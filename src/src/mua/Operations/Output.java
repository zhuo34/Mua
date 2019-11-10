package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Output implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		return args.get(0);
	}
}
