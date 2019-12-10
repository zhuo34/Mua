package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Stop implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		return new MuaNone();
	}
}
