package src.mua.Operations;

import src.mua.Value.List;
import src.mua.Value.Value;

import java.util.ArrayList;
import java.util.HashMap;

public class OperationFactory {

	private static HashMap<String, Operation> mOperationMap = new HashMap<String, Operation>() {{
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
	}};

	public static Operation getOperation(String str) {
		return mOperationMap.get(str);
	}

	public static boolean isOperation(String str) {
		return mOperationMap.containsKey(str);
	}

	public static void addFunction(String name, List argList, List funcBody) {
		ArrayList<String> argListStr = new ArrayList<>();
		for (Value v :argList.getList()) {
			argListStr.add(v.getWord());
		}
		OperationFactory.addFunction(name, argListStr, funcBody.getWord());
	}

	public static void addFunction(String name, ArrayList<String> argList, String funcBody) {
		mOperationMap.put(name, new Function(name, argList, funcBody));
	}

	public static void eraseFunction(String name) {
		mOperationMap.remove(name);
	}
}
