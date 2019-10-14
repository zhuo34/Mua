package src.mua.Operations;

import src.mua.MuaData;
import src.mua.NameSpace;

import java.util.ArrayList;

public class Lt implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaData execute(ArrayList<MuaData> args, NameSpace ns) {
		MuaData ret = args.get(0).compare(args.get(1), '<');
		return ret;
	}
}
