package src.mua.MuaOperation;

import src.mua.Exception.MuaException;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Butfirst implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue v = args.get(0);
		if (v instanceof MuaWord) {
			return new MuaWord(MuaWord.convertFrom(v).subString(1));
		} else if (v instanceof MuaList) {
			return new MuaList(MuaList.convertFrom(v).subList(1));
		}
		throw new MuaException("Runtime Error: '" + v.value() + "' is not a Word or List.");
	}
}
