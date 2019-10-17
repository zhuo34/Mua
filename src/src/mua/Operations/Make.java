package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Make implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = new None();
		ns.make(args.get(0).getWord(), args.get(1));
		return ret;
	}
}
