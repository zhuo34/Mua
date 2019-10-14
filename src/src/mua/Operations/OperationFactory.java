package src.mua.Operations;

import java.util.HashMap;

public class OperationFactory {

	private static HashMap<String, Operation> operationMap = new HashMap<String, Operation>() {{
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
	}};

	public static Operation getOperation(String str) {
		return operationMap.get(str);
	}
}
