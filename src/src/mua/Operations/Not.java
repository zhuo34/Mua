package src.mua.Operations;

import src.mua.MuaData;
import src.mua.NameSpace;

import java.util.ArrayList;

public class Not implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaData execute(ArrayList<MuaData> args, NameSpace ns) {
		MuaData ret = args.get(0).logicOperation('!');
		return ret;
	}
}
