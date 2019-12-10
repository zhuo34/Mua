package src.mua.Operations;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Make implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaValue ret = new MuaNone();
		MuaValue name = args.get(0);
		MuaValue v = args.get(1);
		if (name.canBeName()) {
			OperationFactory.eraseFunction(name.getWord());
			if ((v instanceof MuaList) && v.getList().size() == 2) {
				MuaValue argList = v.getList().get(0);
				MuaValue funcBody = v.getList().get(1);
				if ((argList instanceof MuaList) && (funcBody instanceof MuaList)) {
					OperationFactory.addFunction(name.getWord(), (MuaList)argList, (MuaList)funcBody);
				}
			}
			caller.getNameSpace().make(args.get(0).getWord(), args.get(1));
		}
		return ret;
	}
}
