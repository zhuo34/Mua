package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.lang.reflect.Constructor;
import java.util.Stack;

public class MuaValueFactory {

	private static Stack<MuaList> muaListStack = new Stack<>();
	private static Stack<MuaList> muaExpressionStack = new Stack<>();

	public static MuaValue parseLiteral(String str) throws MuaException {
		MuaValue ret = new MuaNone();
		if (!isParsingList()) {
			if (!isParsingExpression()) {
				if (literalIsNumber(str)) {
					ret = new MuaNumber(Double.parseDouble(str));
				} else if (literalIsWord(str)) {
					ret = new MuaWord(str.substring(1));
				} else if (literalIsBool(str)) {
					ret = new MuaBool(Boolean.parseBoolean(str));
				} else if (literalIsHead(str, MuaList.prefix)) {
					ret = parseList(str);
				} else if (literalIsHead(str, MuaExpression.prefix)) {
					ret = parseExpression(str);
				} else {
					throw new MuaException("Syntax Error: undefined literal '" + str + "'.");
				}
			} else {
				ret = parseExpression(str);
			}
		} else {
			ret = parseList(str);
		}

		return ret;
	}

	private static MuaValue parseList(String str, char prefix, char postfix,
									  Stack<MuaList> stack, MuaList.MuaListInstance newInstance) {
		MuaValue ret = new MuaNone();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < str.length(); i++) {
			char curChar = str.charAt(i);
			if (curChar != prefix && curChar != postfix) {
				sb.append(curChar);
			} else {
				if (sb.length() > 0) {
					stack.peek().add(new MuaWord(sb.toString()));
					sb = new StringBuilder();
				}
				if (str.charAt(i) == prefix) {
					MuaList newMuaList = newInstance.newInstance();
					stack.push(newMuaList);
				} else if (str.charAt(i) == postfix) {
					if (stack.empty()) {
						throw new MuaException("Syntax Error: unmatched '" + postfix + "'.");
					}
					MuaList lastList = stack.pop();
					if (stack.empty()) {
						ret = lastList;
						break;
					} else {
						stack.peek().add(lastList);
					}
				}
			}
		}
		if (sb.length() > 0) {
			stack.peek().add(new MuaWord(sb.toString()));
		}
		return ret;
	}

	public static boolean isParsing() {
		return isParsingList() || isParsingExpression();
	}

	public static boolean isParsingList() {
		return !muaListStack.empty();
	}

	private static MuaValue parseList(String str) {
		return parseList(str, MuaList.prefix, MuaList.postfix, muaListStack, MuaList::new);
	}

	public static boolean isParsingExpression() {
		return !muaExpressionStack.empty();
	}

	private static MuaValue parseExpression(String str) {
		return parseList(str, MuaExpression.prefix, MuaExpression.postfix, muaExpressionStack, MuaExpression::new);
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

	public static boolean literalIsHead(String str, char c) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(0) == c) {
			ret = true;
		}
		return ret;
	}

	public static boolean literalIsTail(String str, char c) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(str.length() - 1) == c) {
			ret = true;
		}
		return ret;
	}

	public static MuaNumber arithmeticOperation(MuaValue v1, MuaValue v2, char op) {
		MuaNumber operand1 = MuaNumber.convertFrom(v1);
		MuaNumber operand2 = MuaNumber.convertFrom(v2);
		if (op == '+') {
			return new MuaNumber(operand1.getNumber() + operand2.getNumber());
		} else if (op == '-') {
			return new MuaNumber(operand1.getNumber() - operand2.getNumber());
		} else if (op == '*') {
			return new MuaNumber(operand1.getNumber() * operand2.getNumber());
		} else if (op == '/') {
			if (operand2.getNumber() == 0) {
				throw new MuaException("Arithmetic Error: divide zero.");
			}
			return new MuaNumber(operand1.getNumber() / operand2.getNumber());
		} else if (op == '%') {
			if (operand2.getNumber() == 0) {
				throw new MuaException("Arithmetic Error: divide zero.");
			}
			return new MuaNumber(operand1.getNumber() % operand2.getNumber());
		}
		throw new MuaException("Arithmetic Error: undefined operator '" + op + "'.");
	}

	public static MuaBool logicOperation(MuaValue v1, MuaValue v2, char op) {
		MuaBool operand1 = MuaBool.convertFrom(v1);
		MuaBool operand2 = MuaBool.convertFrom(v2);
		if (op == '&') {
			return new MuaBool(operand1.getBool() && operand2.getBool());
		} else if (op == '|') {
			return new MuaBool(operand1.getBool() || operand2.getBool());
		} else if (op == '^') {
			return new MuaBool(operand1.getBool() ^ operand2.getBool());
		}
		throw new MuaException("Logic Error: undefined operator '" + op + "'.");
	}

	public static MuaBool logicOperation(MuaValue v, char op) {
		MuaBool operand = MuaBool.convertFrom(v);
		if (op == '!') {
			return new MuaBool(!operand.getBool());
		}
		throw new MuaException("Logic Error: undefined operator '" + op + "'.");
	}

	public static MuaBool compare(MuaValue v1, MuaValue v2, char op) {
		double cmpRes = 0;
		boolean flag = false;
		if (v1 instanceof MuaWord && v2 instanceof MuaWord) {
			cmpRes = MuaWord.convertFrom(v1).getWord().compareTo(MuaWord.convertFrom(v2).getWord());
			flag = true;
		} else if (!(v1 instanceof MuaList) && !(v2 instanceof MuaList)) {
			MuaNumber operand1 = MuaNumber.convertFrom(v1);
			MuaNumber operand2 = MuaNumber.convertFrom(v2);
			cmpRes = operand1.getNumber() - operand2.getNumber();
			flag = true;
		}
		if (flag) {
			if (op == '=') {
				return new MuaBool(cmpRes == 0);
			} else if (op == '<') {
				return new MuaBool(cmpRes < 0);
			} else if (op == '>') {
				return new MuaBool(cmpRes > 0);
			}
		}
		return new MuaBool(false);
	}
}
