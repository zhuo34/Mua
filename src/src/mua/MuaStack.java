package src.mua;

import src.mua.Exception.MuaException;
import src.mua.MuaOperation.MuaOperation;
import src.mua.MuaOperation.Output;
import src.mua.MuaValue.MuaExpression;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaValueFactory;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MuaStack {

	private NameSpace ns;
	private Stack<MuaOperation> opStack = new Stack<>();
	private Stack<Integer> opDataStack = new Stack<>();
	private Stack<MuaValue> dataStack = new Stack<>();

	private boolean stop = false;
	boolean isFunction = false;
	public Scanner scanner;

	public MuaStack(NameSpace ns) {
		this.ns = ns;
	}

	public NameSpace getNameSpace() {
		return this.ns;
	}

	public MuaValue getStatementValue() {
		MuaValue ret = new MuaNone();
		if (this.isFunction) {
			ret = this.getFuncOutput();
		} else if (!dataStack.isEmpty()) {
			ret = dataStack.peek();
		}
		return ret;
	}

	private MuaValue getFuncOutput() {
		return this.ns.output;
	}

	public void parseLine(String line) {
		this.scanner = new Scanner(line);
		while (this.scanner.hasNext()) {
			String str = this.scanner.next();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			if (!MuaValueFactory.isParsing()) {
				this.processStatement(items);
				if (this.stop) {
					if (this.isGlobal()) {
						this.stop = false;
					}
					break;
				}
			}
		}
	}

	public void processStatement(ArrayList<MuaItem> statement) {
		for (MuaItem it: statement) {
			if (it instanceof MuaOperation) {
				opStack.push((MuaOperation)it);
				opDataStack.push(dataStack.size());
			} else if (it instanceof MuaValue) {
				if (it instanceof MuaExpression) {
					((MuaExpression) it).setNamespace(this.ns);
					it = ((MuaExpression) it).getValue();
				}
				dataStack.push((MuaValue) it);
			} else {
				throw new MuaException("Runtime Error: undefined.");
			}
			this.executeUntil();
			if (this.stop) {
				return;
			}
		}
		this.executeUntil();
	}

	private void executeUntil() {
		while (!opStack.isEmpty() && opStack.peek().argNumber() <= dataStack.size() - opDataStack.peek()) {
			MuaOperation topOp = opStack.pop();
			opDataStack.pop();
			ArrayList<MuaValue> argList = new ArrayList<>();
			for (int i = 0; i < topOp.argNumber(); i++) {
				argList.add(0, dataStack.pop());
			}

			MuaValue res = topOp.execute(argList, this);
			if (topOp instanceof Output) {
				this.ns.output = res;
				dataStack.push(new MuaNone());
				continue;
			}

			dataStack.push(res);
			if (res instanceof MuaNone) {
				MuaNone muaNoneRes = (MuaNone) res;
				if (muaNoneRes.info == MuaNone.NoneInfo.Stop) {
					this.stop = true;
					break;
				}
			}
		}
	}

	private boolean isGlobal() {
		return this == Interpreter.globalStack;
	}
}
