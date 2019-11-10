package src.mua.Operations;

import src.mua.MuaItem;
import src.mua.MuaItemFactory;
import src.mua.MuaStack;
import src.mua.NameSpace;
import src.mua.Value.Value;

import javax.naming.Name;
import java.util.ArrayList;

public class Function implements Operation {

	private String mName = "";
	private ArrayList<String> mArgList = new ArrayList<>();
	private ArrayList<String> mFuncBody = new ArrayList<>();
	private NameSpace mLocalNameSpace = new NameSpace();
	private MuaStack mLocalStack = new MuaStack(mLocalNameSpace);

	public Function(String name, ArrayList<String> argList, ArrayList<String> funcBody) {
		mName = name;
		mArgList = argList;
		mFuncBody = funcBody;
	}

	@Override
	public int argNumber() {
		return mArgList.size();
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		this.bind(args);
		ArrayList<MuaItem> muaStatement = new ArrayList<>();
		for (String str : mFuncBody) {
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			muaStatement.addAll(items);
		}
		MuaStack muaStack = new MuaStack(ns);
		return mLocalStack.processStatement(muaStatement);
	}

	private void bind(ArrayList<Value> args) {
		mLocalNameSpace.clear();
		for (int i = 0; i < argNumber(); i++) {
			mLocalNameSpace.make(mArgList.get(i), args.get(i));
		}
	}
}
