package src.mua;

import src.mua.Operations.Operation;
import src.mua.Operations.OperationFactory;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;
import java.util.Stack;

public class MuaStack {

	private NameSpace ns;
	private Stack<Operation> opStack;
	private Stack<Integer> opDataStack;
	private Stack<Value> dataStack;

	public MuaStack(NameSpace ns) {
		this.ns = ns;
		opStack = new Stack<>();
		opDataStack = new Stack<>();
		dataStack = new Stack<>();
	}

	public Value processStatement(ArrayList<MuaItem> statement) {
		this.clear();
		Value ret = new None();
		for (MuaItem it: statement) {
			if (it instanceof Operation) {
				opStack.push((Operation)it);
				opDataStack.push(dataStack.size());
			} else if (it instanceof Value) {
//				((Value) it).print();
//				System.out.print('\t');
//				System.out.println(it instanceof List);
				dataStack.push((Value)it);
				this.executeUntil();
			}
		}

		this.executeUntil();
//		ns.get("a").print();
//		System.out.println(statement.size());
		return ret;
	}

	private void executeUntil() {
		while (!opStack.isEmpty() && opStack.peek().argNumber() <= dataStack.size() - opDataStack.peek()) {
			Operation topOp = opStack.pop();
			opDataStack.pop();
			ArrayList<Value> argList = new ArrayList<>();
			for (int i = 0; i < topOp.argNumber(); i++) {
				argList.add(0, dataStack.pop());
			}

			Value res = topOp.execute(argList, ns);
			if (!(res instanceof None)) {
				dataStack.push(res);
			} else {
				// TODO: error
			}
		}
	}

	private void clear() {
		this.opStack.clear();
		this.opDataStack.clear();
		this.dataStack.clear();
	}
}
