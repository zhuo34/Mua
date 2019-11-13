package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.Value.Bool;
import src.mua.Value.Number;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Isnumber implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		return new Bool(args.get(0) instanceof Number);
	}
}
