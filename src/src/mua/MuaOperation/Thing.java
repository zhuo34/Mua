package src.mua.MuaOperation;

import src.mua.Interpreter;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaWord;
import src.mua.NameSpace;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Thing implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		String name = MuaWord.convertFrom(args.get(0)).getWord();
		MuaValue ret = new MuaNone();
		NameSpace localNameSpace = caller.getNameSpace();
		if (localNameSpace.has(name)) {
			ret = localNameSpace.get(name);
		} else if (Interpreter.globalNameSpace.has(name)) {
			ret = Interpreter.globalNameSpace.get(name);
		}
		return ret;
	}
}
