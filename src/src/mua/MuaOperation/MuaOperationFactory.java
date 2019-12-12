package src.mua.MuaOperation;

import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;
import java.util.HashMap;

public class MuaOperationFactory {

	private static HashMap<String, MuaOperation> mOperationMap = new HashMap<String, MuaOperation>() {{
		put("make", new Make());
		put("print", new Print());
		put("thing", new Thing());
		put("erase", new Erase());
		put("isname", new Isname());
		put("read", new Read());
		put("add", new Add());
		put("sub", new Sub());
		put("mul", new Mul());
		put("div", new Div());
		put("mod", new Mod());
		put("eq", new Eq());
		put("gt", new Gt());
		put("lt", new Lt());
		put("and", new And());
		put("or", new Or());
		put("not", new Not());
		put("readlist", new Readlist());
		put("repeat", new Repeat());
		put("output", new Output());
		put("stop", new Stop());
		put("export", new Export());
		put("isword", new Isword());
		put("isnumber", new Isnumber());
		put("isbool", new Isbool());
		put("islist", new Islist());
		put("isempty", new Isempty());
		put("if", new If());
		put("word", new Word());
		put("sentence", new Sentence());
		put("join", new Join());
		put("first", new First());
		put("last", new Last());
		put("butfirst", new Butfirst());
		put("butlast", new Butlast());
		put("random", new Random());
		put("int", new Int());
		put("sqrt", new Sqrt());
		put("wait", new Wait());
		put("save", new Save());
		put("load", new Load());
		put("erall", new Erall());
		put("poall", new Poall());
	}};

	public static MuaOperation getOperation(String str) {
		return mOperationMap.get(str);
	}

	public static boolean isOperation(String str) {
		return mOperationMap.containsKey(str);
	}

	public static void addFunction(String name, MuaList argMuaList, MuaList funcBody) {
		ArrayList<String> argListStr = new ArrayList<>();
		for (MuaValue v : argMuaList.getList()) {
			argListStr.add(MuaWord.convertFrom(v).getWord());
		}
		MuaOperationFactory.addFunction(name, argListStr, funcBody.contentToString());
	}

	public static void addFunction(String name, ArrayList<String> argList, String funcBody) {
		mOperationMap.put(name, new Function(name, argList, funcBody));
	}

	public static void eraseFunction(String name) {
		mOperationMap.remove(name);
	}
}
