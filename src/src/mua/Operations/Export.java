package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.Main;
import src.mua.MuaStack;
import src.mua.NameSpace;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Export implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		caller.getNameSpace().exportTo(Interpreter.globalNameSpace);
		return new None();
	}
}
