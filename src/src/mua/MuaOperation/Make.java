package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;

public class Make implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaNone();
		MuaWord name = MuaWord.convertFrom(args.get(0));
		MuaValue v = args.get(1);
		if (((MuaWord )name).canBeName()) {
			MuaOperationFactory.eraseFunction(name.getWord());
			if ((v instanceof MuaList) && MuaList.convertFrom(v).size() == 2) {
				MuaValue argList = MuaList.convertFrom(v).get(0);
				MuaValue funcBody = MuaList.convertFrom(v).get(1);
				if ((argList instanceof MuaList) && (funcBody instanceof MuaList)) {
					MuaOperationFactory.addFunction(name.getWord(), (MuaList)argList, (MuaList)funcBody);
				}
			}
			caller.getNameSpace().make(name.getWord(), v);
		}
		return ret;
	}
}
