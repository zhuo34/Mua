package src.mua.Operations;

import src.mua.*;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Repeat implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		int number = (int)args.get(0).getNumber();
		MuaValue list = args.get(1);

		String body = list.getWord();
		StringBuilder sb = new StringBuilder();
		MuaStack muaStack = new MuaStack(caller.getNameSpace());
		while (number-- > 0) {
			sb.append(' ').append(body);
		}

		Interpreter.parseLine(sb.toString(), muaStack);
		return muaStack.getStatementValue();
	}
}
