package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Bool;
import src.mua.Value.Value;
import src.mua.Value.Word;

import java.util.ArrayList;

public class Isword implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		return new Bool(args.get(0) instanceof Word);
	}
}
