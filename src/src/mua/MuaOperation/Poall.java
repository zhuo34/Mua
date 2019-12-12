package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;
import java.util.Set;

public class Poall implements MuaOperation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		Set<String> keys = caller.getNameSpace().getKeys();
		for (String k: keys) {
			System.out.println(k);
		}
		return new MuaNone();
	}
}
