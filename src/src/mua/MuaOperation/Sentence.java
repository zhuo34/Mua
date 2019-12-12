package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Sentence implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		ArrayList<MuaValue> list = new ArrayList<>();
		list.add(args.get(0));
		list.add(args.get(1));
		return new MuaList(list);
	}
}
