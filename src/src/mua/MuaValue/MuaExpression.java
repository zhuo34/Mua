package src.mua.MuaValue;

import src.mua.Exception.MuaException;

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

	public MuaExpression() {
	}

	public MuaNumber toNumber() {
		return getValue();
	}

	@Override
	public void add(MuaValue v) {
		if (v instanceof MuaExpression) {
			this.mList.add(v);
		} else {
			String s = MuaWord.convertFrom(v).getWord();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < s.length(); i++) {
				char curChar = s.charAt(i);
				int idx = ops.indexOf(curChar);
				if (idx > -1) {
					if (sb.length() > 0) {
						super.add(MuaNumber.convertFrom(new MuaWord(sb.toString())));
						sb = new StringBuilder();
					}
					super.add(new MuaWord(Integer.toString(idx)));
				} else {
					sb.append(curChar);
				}
			}
			if (sb.length() > 0) {
				super.add(MuaNumber.convertFrom(new MuaWord(sb.toString())));
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
		Stack<MuaNumber> dataStack = new Stack<>();
		Stack<Integer> opStack = new Stack<>();
		for (int i = 0; i < this.size(); i++) {
			MuaValue v = this.mList.get(i);
			if (v instanceof MuaWord) {
				int opIdx = (int) MuaNumber.convertFrom(v).getNumber();
				if (i == 0 && ops.charAt(opIdx) == '-') {
					MuaNumber firstNumber = MuaNumber.convertFrom(this.mList.get(++i));
					dataStack.push(new MuaNumber(-firstNumber.getNumber()));
				} else {
					if (opStack.isEmpty()) {
						opStack.push(opIdx);
					} else {
						int lastOpIdx = opStack.peek();
						if (getOpLevel(opIdx) <= getOpLevel(lastOpIdx)) {
							if (dataStack.size() < 2) {
								throw new MuaException("Expression Error: no enough operands.");
							} else {
								MuaNumber operand2 = dataStack.pop();
								MuaNumber operand1 = dataStack.pop();
								MuaNumber res = MuaValueFactory.arithmeticOperation(operand1, operand2, ops.charAt(opStack.pop()));
								dataStack.push(res);
							}
						}
						opStack.push(opIdx);
					}
				}
			} else if (v instanceof MuaExpression) {
				dataStack.push(((MuaExpression) v).getValue());
			} else {
				dataStack.push(MuaNumber.convertFrom(v));
			}
		}
		while (!opStack.isEmpty()) {
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
		value = dataStack.pop();
	}

	public MuaNumber getValue() {
		if (!isCalculated) {
			calculate();
		}
		return value;
	}
}
