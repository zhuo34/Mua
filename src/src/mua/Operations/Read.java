package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.MuaStack;
import src.mua.Value.None;
import src.mua.Value.Value;
import src.mua.Value.Word;

import java.util.ArrayList;

public class Read implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		Value ret = new None();
		boolean flag = true;
		if (caller.scanner.hasNext()) {
			ret = new Word(caller.scanner.next());
		}
		return ret;
	}
}
