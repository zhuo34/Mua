package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Output implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		return args.get(0);
	}
}
