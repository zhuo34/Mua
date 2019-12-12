package src.mua.MuaOperation;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Load implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		String path = MuaWord.convertFrom(args.get(0)).getWord();

//		JSONWriter

		return new MuaNone();
	}
}
