package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Value;
import src.mua.Value.ValueFactory;

import java.util.ArrayList;

public class Not implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = ValueFactory.logicOperation(args.get(0), '!');
		return ret;
	}
}
