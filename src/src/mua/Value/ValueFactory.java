package src.mua.Value;

import java.util.Stack;

public class ValueFactory {

	private static Stack<List> listStack = new Stack<>();

	public static Value parseLiteral(String str) {
		Value ret = new None();
		if (listStack.empty()) {
			if (ValueFactory.literalIsNumber(str)) {
				ret = new Number(Double.parseDouble(str));
			} else if (ValueFactory.literalIsWord(str)) {
				ret = new Word(str.substring(1));
			} else if (ValueFactory.literalIsBool(str)) {
				ret = new Bool(Boolean.parseBoolean(str));
			} else if (ValueFactory.literalIsListHead(str)) {
				ret = ValueFactory.parseList(str);
			}
		} else {
			ret = ValueFactory.parseList(str);
		}

		return ret;
	}

	public static boolean isParsingList() {
		return !listStack.empty();
	}

	private static Value parseList(String str) {
		Value ret = new None();
		if (!literalIsListHead(str) && !literalIsListTail(str)) {
			if (!str.isEmpty()) {
				listStack.peek().add(new Word(str));
			}
		} else if (literalIsListHead(str)) {
			List newList = new List();
			listStack.push(newList);
			ret = parseList(str.substring(1));
		} else {
			int cnt = 0;
			while (literalIsListTail(str)) {
				cnt++;
				str = str.substring(0, str.length()-1);
			}
			if (!str.isEmpty()) {
				listStack.peek().add(new Word(str));
			}
			for (int i = 0; i < cnt; i++) {
				List lastList = listStack.pop();
				if (listStack.empty()) {
					ret = lastList;
				} else {
					listStack.peek().add(lastList);
				}
			}
		}
		return ret;
	}

	public static boolean literalIsWord(String str) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(0) == '"') {
			ret = true;
		}
		return ret;
	}

	public static boolean literalIsNumber(String str) {
		boolean ret = false;
		if (str.matches("-?[0-9]+(.[0-9]+)?")) {
			ret = true;
		}
		return ret;
	}

	public static boolean literalIsBool(String str) {
		boolean ret = false;
		if (str.equals("true") || str.equals("false")) {
			ret = true;
		}
		return ret;
	}

	public static boolean literalIsListHead(String str) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(0) == '[') {
			ret = true;
		}
		return ret;
	}

	public static boolean literalIsListTail(String str) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(str.length() - 1) == ']') {
			ret = true;
		}
		return ret;
	}

	public static Value arithmeticOperation(Value v1,Value v2, char op) {
		Value ret = new None();
		Value operand1 = v1.toNumber();
		Value operand2 = v2.toNumber();
		if (operand1 instanceof Number && operand2 instanceof Number) {
			if (op == '+') {
				ret = new Number(operand1.getNumber() + operand2.getNumber());
			} else if (op == '-') {
				ret = new Number(operand1.getNumber() - operand2.getNumber());
			} else if (op == '*') {
				ret = new Number(operand1.getNumber() * operand2.getNumber());
			} else if (op == '/') {
				// TODO: error /0
				ret = new Number(operand1.getNumber() / operand2.getNumber());
			} else if (op == '%') {
				// TODO: error %0
				ret = new Number(operand1.getNumber() % operand2.getNumber());
			}
		}
		return ret;
	}

	public static Value logicOperation(Value v1, Value v2, char op) {
		Value ret = new None();
		Value operand1 = v1.toBool();
		Value operand2 = v2.toBool();
		if (operand1 instanceof Bool && operand2 instanceof Bool) {
			if (op == '&') {
				ret = new Bool(operand1.getBool() && operand2.getBool());
			} else if (op == '|') {
				ret = new Bool(operand1.getBool() || operand2.getBool());
			} else if (op == '^') {
				ret = new Bool(operand1.getBool() ^ operand2.getBool());
			}
		}
		return ret;
	}

	public static Value logicOperation(Value v, char op) {
		Value ret = new None();
		if (v instanceof Bool) {
			if (op == '!') {
				ret = new Bool(!v.getBool());
			}
		}
		return ret;
	}

	public static Value compare(Value v1, Value v2, char op) {
		Value ret = new Bool(false);
		double cmpRes = 0;
		boolean flag = false;
		if (v1 instanceof Number && v2 instanceof Number) {
			cmpRes = v1.getNumber() - v2.getNumber();
			flag = true;
		} else if (v1 instanceof Word && v2 instanceof Word) {
			cmpRes = v1.getWord().compareTo(v2.getWord());
			flag = true;
		} else if (!(v1 instanceof Bool) && !(v2 instanceof Bool)) {
			Value operand1 = v1.toNumber();
			Value operand2 = v2.toNumber();
			if (operand1 instanceof Number && operand2 instanceof Number) {
				cmpRes = operand1.getNumber() - operand2.getNumber();
				flag = true;
			}
		}
		if (flag) {
			if (op == '=') {
				ret = new Bool(cmpRes == 0);
			} else if (op == '<') {
				ret = new Bool(cmpRes < 0);
			} else if (op == '>') {
				ret = new Bool(cmpRes > 0);
			}
		}
		return ret;
	}
}
