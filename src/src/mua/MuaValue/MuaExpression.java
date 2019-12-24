package src.mua.MuaValue;

import src.mua.Exception.MuaException;
import src.mua.MuaItem;
import src.mua.MuaOperation.MuaOperation;
import src.mua.MuaOperation.MuaOperationFactory;
import src.mua.MuaStack;
import src.mua.NameSpace;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Stack;

public class MuaExpression extends MuaList {
	public static final char prefix = '(';
	public static final char postfix = ')';
	public static final String ops = "+- %*/";
	public static final int opLevel = 2;
	public static final int opMaxCntAtOneLevel = ops.length() / opLevel;

	private MuaNumber value;
	public boolean isCalculated = false;
	private Stack<MuaNumber> dataStack = new Stack<>();
	private Stack<Integer> opStack = new Stack<>();

	private NameSpace ns = new NameSpace();

	public MuaExpression() {
	}

	public MuaNumber toNumber() {
		return this.getValue();
	}

	@Override
	public String listToString() {
		return this.listToString(String.valueOf(prefix), String.valueOf(postfix));
	}

	@Override
	public void add(MuaValue v) {
		if (v instanceof MuaExpression) {
			super.add(v);
		} else {
			String s = MuaWord.convertFrom(v).getWord();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				char curChar = s.charAt(i);
				boolean isOp = ops.contains(String.valueOf(curChar));
				if (isOp) {
					if (sb.length() > 0) {
						MuaValue tmp = new MuaWord(sb.toString());
						try {
							tmp = MuaNumber.convertFrom(tmp);
						} catch (MuaException e) {}
						super.add(tmp);
						sb = new StringBuilder();
					}
					super.add(new MuaWord(curChar));
				} else {
					sb.append(curChar);
				}
			}
			if (sb.length() > 0) {
				MuaValue tmp = new MuaWord(sb.toString());
				try {
					tmp = MuaNumber.convertFrom(tmp);
				} catch (MuaException e) {}
				super.add(tmp);
			}
		}
	}

	private int getOpLevel(char op) {
		return getOpLevel(ops.indexOf(op));
	}

	private int getOpLevel(int opIndex) {
		int ret = -1;
		if (opIndex > -1) {
			ret = opIndex / opMaxCntAtOneLevel;
		}
		return ret;
	}

	private void calculate() {
//		System.out.println(this.value());
		StringBuilder subBody = new StringBuilder();
		boolean fu = false;
		for (int i = 0; i < this.size(); i++) {
			MuaValue v = this.get(i);
			if (v instanceof MuaWord) {
				String str = MuaWord.convertFrom(v).getWord();
				if (ops.contains(str)) {
					int opIdx = (int) ops.indexOf(str);
					boolean isFu = ops.charAt(opIdx) == '-';
					boolean condition1 = i == 0 || ops.contains(MuaWord.convertFrom(this.get(i - 1)).getWord());
					if (isFu && condition1) {
						fu = !fu;
					} else {
						if (subBody.length() != 0) {
							MuaStack stack = new MuaStack(this.ns);
//							System.out.print("sub body: ");
//							System.out.println(subBody.toString());
							stack.parseLine(subBody.toString());
							dataStack.push(MuaNumber.convertFrom(stack.getStatementValue()));
							subBody = new StringBuilder();
						}
						if (opStack.isEmpty()) {
							opStack.push(opIdx);
						} else {
							int lastOpIdx = opStack.peek();
							if (getOpLevel(opIdx) <= getOpLevel(lastOpIdx)) {
								this.calculate_once();
							}
							opStack.push(opIdx);
						}
					}
				} else {
//					System.out.println(str);
					subBody.append(str).append(' ');
				}
			} else {
				if (v instanceof MuaExpression) {
					((MuaExpression) v).setNamespace(this.ns);
				}
				MuaNumber thisNum = MuaNumber.convertFrom(v);
				if (subBody.length() == 0) {
					if (fu) {
						thisNum = thisNum.opposite();
						fu = false;
					}
					dataStack.push(thisNum);
				} else {
					subBody.append(String.valueOf(thisNum.getNumber())).append(' ');
				}
			}
		}
		if (subBody.length() > 0) {
			MuaStack stack = new MuaStack(this.ns);
			stack.parseLine(subBody.toString());
			dataStack.push(MuaNumber.convertFrom(stack.getStatementValue()));
		}
		while (!opStack.isEmpty()) {
			this.calculate_once();
		}
		value = dataStack.pop();
	}

	private void calculate_once() {
		int opIdx = opStack.pop();
		if (dataStack.size() < 2) {
			throw new MuaException("Expression Error: no enough operands.");
		} else {
			MuaNumber operand2 = dataStack.pop();
			MuaNumber operand1 = dataStack.pop();
			MuaNumber res = MuaValueFactory.arithmeticOperation(operand1, operand2, ops.charAt(opIdx));
			dataStack.push(res);
		}
	}

	public MuaNumber getValue() {
		if (!isCalculated) {
			this.calculate();
		}
		return value;
	}

	public void setNamespace(NameSpace ns) {
		this.ns = ns;
		this.isCalculated = false;
	}
}
