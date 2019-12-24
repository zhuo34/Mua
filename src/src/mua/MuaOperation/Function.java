package src.mua.MuaOperation;

import src.mua.*;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Function implements MuaOperation {

	private String mName = "";
	private ArrayList<String> mArgList = new ArrayList<>();
	private String mFuncBody = "";

	public Function(String name, ArrayList<String> argList, String funcBody) {
		mName = name;
		mArgList = argList;
		mFuncBody = funcBody;
	}

	@Override
	public int argNumber() {
		return mArgList.size();
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		NameSpace ns = new NameSpace();
		ns.bind(mArgList, args);
		MuaStack stack = new FunctionStack(ns);
		stack.parseLine(mFuncBody);
		return stack.getStatementValue();
	}
}
