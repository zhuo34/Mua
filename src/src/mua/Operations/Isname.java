package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaBool;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Isname implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaBool(caller.getNameSpace().has(args.get(0).getWord()));
		return ret;
	}
}
