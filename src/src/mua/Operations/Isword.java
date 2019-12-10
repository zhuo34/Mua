package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaBool;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Isword implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		return new MuaBool(args.get(0) instanceof MuaWord);
	}
}
