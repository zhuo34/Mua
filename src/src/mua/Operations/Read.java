package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Read implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaNone();
		boolean flag = true;
		if (caller.scanner.hasNext()) {
			ret = new MuaWord(caller.scanner.next());
		}
		return ret;
	}
}
