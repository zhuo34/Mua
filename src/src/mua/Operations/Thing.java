package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.Main;
import src.mua.MuaStack;
import src.mua.NameSpace;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Thing implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		String name = args.get(0).getWord();
		Value ret = new None();
		NameSpace localNameSpace = caller.getNameSpace();
		if (localNameSpace.has(name)) {
			ret = localNameSpace.get(name);
		} else if (Interpreter.globalNameSpace.has(name)) {
			ret = Interpreter.globalNameSpace.get(name);
		}
		return ret;
	}
}
