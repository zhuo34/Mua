package src.mua.Operations;

import src.mua.*;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class If implements Operation {
	@Override
	public int argNumber() {
		return 3;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		boolean cond = args.get(0).toBool().getBool();
		Value list1 = args.get(1);
		Value list2 = args.get(2);
		Value list;
		if (cond) {
			list = list1;
		} else {
			list = list2;
		}
		String body = list.getWord();
		Interpreter.parseLine(body, caller);

		return new None();
	}
}
