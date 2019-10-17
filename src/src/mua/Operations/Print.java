package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Print implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = new None();
		Value arg = args.get(0);
		arg.print();
		System.out.println("");
		return ret;
	}
}
