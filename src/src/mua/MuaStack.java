package src.mua;

import src.mua.Operations.Operation;
import src.mua.Operations.OperationFactory;
import src.mua.Operations.Return;
import src.mua.Operations.Stop;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;
import java.util.Stack;

public class MuaStack {

	private NameSpace ns;
	private Stack<Operation> opStack = new Stack<>();
	private Stack<Integer> opDataStack = new Stack<>();
	private Stack<Value> dataStack = new Stack<>();

	public MuaStack(NameSpace ns) {
		this.ns = ns;
	}

	public Value getStatementValue() {
		Value ret = new None();
		if (!dataStack.empty()) {
			ret = dataStack.peek();
		}
		return ret;
	}

	public Value processStatement(ArrayList<MuaItem> statement) {
		this.clear();
		for (MuaItem it: statement) {
			if (it instanceof Operation) {
				opStack.push((Operation)it);
				opDataStack.push(dataStack.size());
			} else if (it instanceof Value) {
				dataStack.push((Value)it);
			} else {
				// TODO: error
				continue;
			}
			if (this.executeUntil() > 0) {
				return this.getStatementValue();
			}
		}
		if (this.executeUntil() > 0) {
			return this.getStatementValue();
		}
		return this.getStatementValue();
	}

	private int executeUntil() {
		int ret = 0;
		while (!opStack.isEmpty() && opStack.peek().argNumber() <= dataStack.size() - opDataStack.peek()) {
			Operation topOp = opStack.pop();
			opDataStack.pop();
			ArrayList<Value> argList = new ArrayList<>();
			for (int i = 0; i < topOp.argNumber(); i++) {
				argList.add(0, dataStack.pop());
			}

			Value res = topOp.execute(argList, ns);
			if (topOp instanceof Stop) {
				dataStack.push(res);
				ret = 1;
				break;
			} else if (topOp instanceof Return) {
				dataStack.push(res);
				ret = 2;
				break;
			} else {
				if (!(res instanceof None)) {
					dataStack.push(res);
				} else {
					// TODO: error
				}
			}
		}
		return ret;
	}

	private void clear() {
		this.opStack.clear();
		this.opDataStack.clear();
		this.dataStack.clear();
	}
}
