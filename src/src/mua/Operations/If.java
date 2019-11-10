package src.mua.Operations;

import src.mua.MuaItem;
import src.mua.MuaItemFactory;
import src.mua.MuaStack;
import src.mua.NameSpace;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class If implements Operation {
	@Override
	public int argNumber() {
		return 3;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		boolean cond = args.get(0).toBool().getBool();
		ArrayList<Value> list1 = args.get(1).getList();
		ArrayList<Value> list2 = args.get(2).getList();
		ArrayList<MuaItem> muaStatement = new ArrayList<>();
		ArrayList<Value> list;
		if (cond) {
			list = list1;
		} else {
			list = list2;
		}
		for (Value v : list) {
			String str = v.toWord().getWord();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			muaStatement.addAll(items);
		}
		MuaStack muaStack = new MuaStack(ns);
		muaStack.processStatement(muaStatement);

		return new None();
	}
}
