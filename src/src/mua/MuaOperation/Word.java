package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Word implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaWord w0 = MuaWord.convertFrom(args.get(0));
		MuaWord w1 = MuaWord.convertFrom(args.get(1));
		return MuaWord.concatenate(w0, w1);
	}
}
