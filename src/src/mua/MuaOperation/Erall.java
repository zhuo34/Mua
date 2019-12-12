package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Erall implements MuaOperation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		caller.getNameSpace().clear();
		return new MuaNone();
	}
}
