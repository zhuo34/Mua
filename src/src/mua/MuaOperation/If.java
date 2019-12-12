package src.mua.MuaOperation;

import src.mua.*;
import src.mua.MuaValue.MuaBool;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class If implements MuaOperation {
	@Override
	public int argNumber() {
		return 3;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		boolean cond = MuaBool.convertFrom(args.get(0)).getBool();

		MuaValue list1 = args.get(1);
		MuaValue list2 = args.get(2);
		MuaValue list;
		if (cond) {
			list = list1;
		} else {
			list = list2;
		}
		String body = MuaList.convertFrom(list).contentToString();

		MuaStack muaStack = new MuaStack(caller.getNameSpace());
		muaStack.parseLine(body);

		return muaStack.getStatementValue();
	}
}
