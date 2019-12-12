package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Stop implements MuaOperation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaNone ret = new MuaNone();
		ret.info = MuaNone.NoneInfo.Stop;
		return ret;
	}
}
