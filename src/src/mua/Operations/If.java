package src.mua.Operations;

import src.mua.*;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class If implements Operation {
	@Override
	public int argNumber() {
		return 3;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		boolean cond = args.get(0).toBool().getBool();
		MuaValue list1 = args.get(1);
		MuaValue list2 = args.get(2);
		MuaValue list;
		if (cond) {
			list = list1;
		} else {
			list = list2;
		}
		String body = list.getWord();
		MuaStack muaStack = new MuaStack(caller.getNameSpace());
		Interpreter.parseLine(body, muaStack);

		return muaStack.getStatementValue();
	}
}
