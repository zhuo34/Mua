package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.Value.Bool;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Isname implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		Value ret = new Bool(caller.getNameSpace().has(args.get(0).getWord()));
		return ret;
	}
}
