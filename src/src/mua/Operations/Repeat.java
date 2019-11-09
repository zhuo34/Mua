package src.mua.Operations;

import src.mua.MuaItem;
import src.mua.MuaItemFactory;
import src.mua.MuaStack;
import src.mua.NameSpace;
import src.mua.Value.List;
import src.mua.Value.Value;

import java.util.ArrayList;
import java.util.Scanner;

public class Repeat implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		int number = (int)args.get(0).getNumber();
		ArrayList<Value> list = args.get(1).getList();
		ArrayList<MuaItem> muaStatement = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		for (Value v : list) {
			sb.append(v.toWord().getWord()).append(' ');
		}
		Scanner scanner = new Scanner(sb.toString());
		while (scanner.hasNext()) {
			String str = scanner.next();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			muaStatement.addAll(items);
		};

		MuaStack muaStack = new MuaStack(ns);
		while (number-- > 0) {
			muaStack.processStatement(muaStatement);
		}

		return null;
	}
}
