package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaValueFactory;

import java.util.ArrayList;

public class And implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = MuaValueFactory.logicOperation(args.get(0), args.get(1), '&');
		return ret;
	}
}
