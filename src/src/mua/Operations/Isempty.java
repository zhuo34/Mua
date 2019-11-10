package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.Bool;
import src.mua.Value.List;
import src.mua.Value.Value;
import src.mua.Value.Word;

import java.util.ArrayList;

public class Isempty implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value v = args.get(0);
		boolean res = false;
		if (v instanceof Word) {
			res = v.getWord().isEmpty();
		} else if (v instanceof List) {
			res = v.getList().isEmpty();
		}
		return new Bool(res);
	}
}
