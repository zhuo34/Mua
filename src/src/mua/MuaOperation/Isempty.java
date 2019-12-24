package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaBool;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Isempty implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue v = args.get(0);
		boolean res = false;
		if (v instanceof MuaWord) {
			res = MuaWord.convertFrom(v).getWord().isEmpty();
		} else if (v instanceof MuaList) {
			res = MuaList.convertFrom(v).isEmpty();
		}
		return new MuaBool(res);
	}
}
