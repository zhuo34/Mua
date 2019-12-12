package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Join implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaList list = MuaList.convertFrom(args.get(0));
		list.add(args.get(1));
		return list;
	}
}
