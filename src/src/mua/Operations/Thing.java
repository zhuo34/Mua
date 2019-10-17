package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Thing implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = ns.get(args.get(0).getWord());
		return ret;
	}
}
