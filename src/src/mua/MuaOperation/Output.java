package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Output implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		return args.get(0);
	}
}
