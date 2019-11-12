package src.mua.Operations;

import src.mua.Main;
import src.mua.NameSpace;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Thing implements Operation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		String name = args.get(0).getWord();
		Value ret = new None();
		if (ns.has(name)) {
			ret = ns.get(name);
		} else if (Main.interpreter.globalNameSpace.has(name)) {
			ret = Main.interpreter.globalNameSpace.get(name);
		}
		return ret;
	}
}
