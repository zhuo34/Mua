package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Erase implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaNone();
		MuaWord name = MuaWord.convertFrom(args.get(0));
		caller.getNameSpace().erase(name.getWord());
		MuaOperationFactory.eraseFunction(name.getWord());
		return ret;
	}
}
