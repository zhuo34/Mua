package src.mua;

import src.mua.Operations.Operation;
import src.mua.Operations.OperationFactory;

import java.util.ArrayList;
import java.util.Stack;

public class MuaStack {

	private NameSpace ns;
	private Stack<Operation> opStack;
	private Stack<Integer> opDataStack;
	private Stack<MuaData> dataStack;

	public MuaStack(NameSpace ns) {
		this.ns = ns;
		opStack = new Stack<>();
		opDataStack = new Stack<>();
		dataStack = new Stack<>();
	}

	public MuaData processStatement(ArrayList<MuaItem> statement) {
		MuaData ret = new MuaData();
		for (MuaItem it: statement) {
			if (it.isOp()) {
				opStack.push(OperationFactory.getOperation(it.getOp()));
				opDataStack.push(dataStack.size());
			} else if (it.isData()) {
//				Operation topOp = opStack.peek();
//				if (dataStack.size() + 1 == topOp.argNumber()) {
//					ArrayList<MuaData> argList = new ArrayList<>();
//					argList.add(0, it.getData());
//					for (int i = 0; i < topOp.argNumber() - 1; i++) {
//						argList.add(0, dataStack.pop());
//					}
//					dataStack.push(topOp.execute(argList, ns));
//					opStack.pop();
//				} else {
				dataStack.push(it.getData());
				executeUntil();
//				}
			}
		}

		executeUntil();

		return ret;
	}

	private void executeUntil() {
		while (!opStack.isEmpty() && opStack.peek().argNumber() <= dataStack.size() - opDataStack.peek()) {
			Operation topOp = opStack.pop();
			opDataStack.pop();
			ArrayList<MuaData> argList = new ArrayList<>();
			for (int i = 0; i < topOp.argNumber(); i++) {
				argList.add(0, dataStack.pop());
			}
			dataStack.push(topOp.execute(argList, ns));
		}
	}
}
