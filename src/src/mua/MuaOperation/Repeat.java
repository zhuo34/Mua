package src.mua.MuaOperation;

import src.mua.*;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaNumber;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Repeat implements MuaOperation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		int number = (int) MuaNumber.convertFrom(args.get(0)).getNumber();
		String body = MuaList.convertFrom(args.get(1)).contentToString();

		StringBuilder sb = new StringBuilder();
		MuaStack muaStack = new MuaStack(caller.getNameSpace());
		while (number-- > 0) {
			sb.append(' ').append(body);
		}

		muaStack.parseLine(sb.toString());
		return muaStack.getStatementValue();
	}
}
