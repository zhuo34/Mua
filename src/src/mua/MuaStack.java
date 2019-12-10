package src.mua;

import src.mua.Operations.Operation;
import src.mua.Operations.Output;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class MuaStack {

	private NameSpace ns;
	private Stack<Operation> opStack = new Stack<>();
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
		if (this.isFunction && !(this.getFuncOutput() instanceof MuaNone)) {
			ret = this.getFuncOutput();
		} else if (!dataStack.empty()) {
			ret = dataStack.peek();
		}
		return ret;
	}

	private MuaValue getFuncOutput() {
		return this.ns.output;
	}

	public void processStatement(ArrayList<MuaItem> statement) {
//		this.clear();
		for (MuaItem it: statement) {
			if (it instanceof Operation) {
				opStack.push((Operation)it);
				opDataStack.push(dataStack.size());
			} else if (it instanceof MuaValue) {
				dataStack.push((MuaValue)it);
			} else {
				// TODO: error
				continue;
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
			Operation topOp = opStack.pop();
			opDataStack.pop();
			ArrayList<MuaValue> argList = new ArrayList<>();
			for (int i = 0; i < topOp.argNumber(); i++) {
				argList.add(0, dataStack.pop());
			}

			MuaValue res = topOp.execute(argList, this);
			if (topOp instanceof Output) {
				this.ns.output = res;
				continue;
			}
			if (!(res instanceof MuaNone)) {
				dataStack.push(res);
			} else {
				MuaNone muaNoneRes = (MuaNone) res;
				if (muaNoneRes.info == MuaNone.NoneInfo.Stop) {
					this.stop = true;
					if (!this.isFunction) {
						dataStack.push(res);
					}
					break;
				}
				// TODO: error
			}
		}
	}

//	private void clear() {
//		this.opStack.clear();
//		this.opDataStack.clear();
//		this.dataStack.clear();
//	}
}
