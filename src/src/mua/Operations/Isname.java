package src.mua.Operations;

import src.mua.MuaData;
import src.mua.NameSpace;

import java.util.ArrayList;

public class Isname implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaData execute(ArrayList<MuaData> args, NameSpace ns) {
		MuaData ret = new MuaData(ns.has(args.get(0).getWord()));
		return ret;
	}
}
