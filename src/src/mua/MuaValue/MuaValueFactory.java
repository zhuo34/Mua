package src.mua.MuaValue;

import java.util.Stack;

public class MuaValueFactory {

	private static Stack<MuaList> muaListStack = new Stack<>();

	public static MuaValue parseLiteral(String str) {
		MuaValue ret = new MuaNone();
		if (!MuaValueFactory.isParsingList()) {
			if (MuaValueFactory.literalIsNumber(str)) {
				ret = new MuaNumber(Double.parseDouble(str));
			} else if (MuaValueFactory.literalIsWord(str)) {
				ret = new MuaWord(str.substring(1));
			} else if (MuaValueFactory.literalIsBool(str)) {
				ret = new MuaBool(Boolean.parseBoolean(str));
			} else if (MuaValueFactory.literalIsListHead(str)) {
				ret = MuaValueFactory.parseList(str);
			}
		} else {
			ret = MuaValueFactory.parseList(str);
		}

		return ret;
	}

	public static boolean isParsingList() {
		return !muaListStack.empty();
	}

	private static void addWordToList(String str) {
		if (!str.isEmpty() && !muaListStack.empty()) {
			if (str.contains("[") || str.contains("]")) {
				// TODO: error
			} else {
				muaListStack.peek().add(new MuaWord(str));
			}
		}
	}

	private static MuaValue parseList(String str) {
		MuaValue ret = new MuaNone();
		if (!literalIsListHead(str) && !literalIsListTail(str)) {
			MuaValueFactory.addWordToList(str);
		} else if (literalIsListHead(str)) {
			MuaList newMuaList = new MuaList();
			muaListStack.push(newMuaList);
			ret = parseList(str.substring(1));
		} else {
			int cnt = 0;
			while (literalIsListTail(str)) {
				cnt++;
				str = str.substring(0, str.length()-1);
			}
			if (cnt > muaListStack.size()) {
				// TODO: error
			}
			MuaValueFactory.addWordToList(str);
			for (int i = 0; i < cnt; i++) {
				MuaList lastMuaList = muaListStack.pop();
				if (muaListStack.empty()) {
					ret = lastMuaList;
				} else {
					muaListStack.peek().add(lastMuaList);
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

	public static MuaValue arithmeticOperation(MuaValue v1, MuaValue v2, char op) {
		MuaValue ret = new MuaNone();
		MuaValue operand1 = v1.toNumber();
		MuaValue operand2 = v2.toNumber();
		if (operand1 instanceof MuaNumber && operand2 instanceof MuaNumber) {
			if (op == '+') {
				ret = new MuaNumber(operand1.getNumber() + operand2.getNumber());
			} else if (op == '-') {
				ret = new MuaNumber(operand1.getNumber() - operand2.getNumber());
			} else if (op == '*') {
				ret = new MuaNumber(operand1.getNumber() * operand2.getNumber());
			} else if (op == '/') {
				// TODO: error /0
				ret = new MuaNumber(operand1.getNumber() / operand2.getNumber());
			} else if (op == '%') {
				// TODO: error %0
				ret = new MuaNumber(operand1.getNumber() % operand2.getNumber());
			}
		}
		return ret;
	}

	public static MuaValue logicOperation(MuaValue v1, MuaValue v2, char op) {
		MuaValue ret = new MuaNone();
		MuaValue operand1 = v1.toBool();
		MuaValue operand2 = v2.toBool();
		if (operand1 instanceof MuaBool && operand2 instanceof MuaBool) {
			if (op == '&') {
				ret = new MuaBool(operand1.getBool() && operand2.getBool());
			} else if (op == '|') {
				ret = new MuaBool(operand1.getBool() || operand2.getBool());
			} else if (op == '^') {
				ret = new MuaBool(operand1.getBool() ^ operand2.getBool());
			}
		}
		return ret;
	}

	public static MuaValue logicOperation(MuaValue v, char op) {
		MuaValue ret = new MuaNone();
		if (v instanceof MuaBool) {
			if (op == '!') {
				ret = new MuaBool(!v.getBool());
			}
		}
		return ret;
	}

	public static MuaValue compare(MuaValue v1, MuaValue v2, char op) {
		MuaValue ret = new MuaBool(false);
		double cmpRes = 0;
		boolean flag = false;
		if (v1 instanceof MuaNumber && v2 instanceof MuaNumber) {
			cmpRes = v1.getNumber() - v2.getNumber();
			flag = true;
		} else if (v1 instanceof MuaWord && v2 instanceof MuaWord) {
			cmpRes = v1.getWord().compareTo(v2.getWord());
			flag = true;
		} else if (!(v1 instanceof MuaBool) && !(v2 instanceof MuaBool)) {
			MuaValue operand1 = v1.toNumber();
			MuaValue operand2 = v2.toNumber();
			if (operand1 instanceof MuaNumber && operand2 instanceof MuaNumber) {
				cmpRes = operand1.getNumber() - operand2.getNumber();
				flag = true;
			}
		}
		if (flag) {
			if (op == '=') {
				ret = new MuaBool(cmpRes == 0);
			} else if (op == '<') {
				ret = new MuaBool(cmpRes < 0);
			} else if (op == '>') {
				ret = new MuaBool(cmpRes > 0);
			}
		}
		return ret;
	}
}
