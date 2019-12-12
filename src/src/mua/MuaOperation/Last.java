package src.mua.MuaOperation;

import src.mua.Exception.MuaException;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Last implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue v = args.get(0);
		if (v instanceof MuaWord) {
			MuaWord word = MuaWord.convertFrom(v);
			return new MuaWord(word.charAt(word.length()-1));
		} else if (v instanceof MuaList) {
			MuaList list = MuaList.convertFrom(v);
			return list.get(list.size()-1);
		}
		throw new MuaException("Runtime Error: '" + v.value() + "' is not a Word or List.");
	}
}
