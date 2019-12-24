package src.mua.MuaOperation;

import src.mua.Exception.MuaException;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Butlast implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaNone();
		MuaValue v = args.get(0);
		if (v instanceof MuaList) {
			MuaList list = MuaList.convertFrom(v);
			if (list.isEmpty() || list.size() == 1) {
				ret = new MuaList();
			} else {
				ret = new MuaList(list.subList(0, list.size()-1));
			}
		} else {
			MuaWord word = MuaWord.convertFrom(v);
			if (word.isEmpty() || word.length() == 1) {
				ret = new MuaWord("");
			} else {
				ret = new MuaWord(word.subString(0, word.length()-1));
			}
		}
		return ret;
	}
}
