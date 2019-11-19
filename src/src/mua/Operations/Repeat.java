package src.mua.Operations;

import src.mua.*;
import src.mua.Value.None;
import src.mua.Value.Value;
import src.mua.Value.List;

import java.util.ArrayList;
import java.util.Scanner;

public class Repeat implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		int number = (int)args.get(0).getNumber();
		Value list = args.get(1);

		MuaStack muaStack = new MuaStack(caller.getNameSpace());
		while (number-- > 0) {
			Interpreter.parseLine(list.getWord(), muaStack);
		}

		return new None();
	}
}
