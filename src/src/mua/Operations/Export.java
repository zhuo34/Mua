package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Export implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		String name = args.get(0).getWord();
		if (caller.getNameSpace().has(name)) {
			Interpreter.globalNameSpace.make(name, caller.getNameSpace().get(name));
		}
		return new MuaNone();
	}
}
