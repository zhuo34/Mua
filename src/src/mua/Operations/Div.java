package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Value;
import src.mua.Value.ValueFactory;

import java.util.ArrayList;

public class Div implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = ValueFactory.arithmeticOperation(args.get(0), args.get(1), '/');
		return ret;
	}
}
