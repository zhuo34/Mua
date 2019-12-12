package src.mua.MuaOperation;

import src.mua.*;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Function implements MuaOperation {

	private String mName = "";
	private ArrayList<String> mArgList = new ArrayList<>();
	private String mFuncBody = "";
	private NameSpace mLocalNameSpace = new NameSpace();
	private MuaStack mLocalStack = new FunctionStack(mLocalNameSpace);

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
		this.bind(args);
		mLocalStack.parseLine(mFuncBody);
		return mLocalStack.getStatementValue();
	}

	private void bind(ArrayList<MuaValue> args) {
		mLocalNameSpace.clear();
		for (int i = 0; i < argNumber(); i++) {
			mLocalNameSpace.make(mArgList.get(i), args.get(i));
		}
	}
}
