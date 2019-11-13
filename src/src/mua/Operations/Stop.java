package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Stop implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		return new None();
	}
}
