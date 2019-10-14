package src.mua;

public class MuaData {

	public enum Type {
		None, Word, Number, Bool
	}

	private Type type = Type.None;
	private String word = "";
	private double number = 0;
	private boolean bool = false;

	public MuaData() {

	}

	public MuaData(String word) {
		this.type = Type.Word;
		this.word = word;
	}

	public MuaData(double number) {
		this.type = Type.Number;
		this.number = number;
	}

	public MuaData(boolean bool) {
		this.type = Type.Bool;
		this.bool = bool;
	}

	public static MuaData parseLiteral(String str) {
		MuaData ret = new MuaData();
		if (MuaData.isNumber(str)) {
			ret.type = Type.Number;
			ret.number = Double.parseDouble(str);
		} else if (MuaData.isWord(str)) {
			ret.type = Type.Word;
			ret.word = str.substring(1);
		} else if (MuaData.isBool(str)) {
			ret.type = Type.Bool;
			if (str.equals("true")) {
				ret.bool = true;
			}
		}
		return ret;
	}

	public static MuaData parseInput(String str) {
		MuaData ret = new MuaData();
		if (MuaData.isNumber(str)) {
			ret.type = Type.Number;
			ret.number = Double.parseDouble(str);
		} else {
			ret.type = Type.Word;
			ret.word = str;
		}
		return ret;
	}

	public static boolean isMuaData(String str) {
		boolean ret = false;
		if (MuaData.isWord(str) || MuaData.isNumber(str) || MuaData.isBool(str)) {
			ret = true;
		}
		return ret;
	}

	public static boolean isWord(String str) {
		boolean ret = false;
		if (!str.isEmpty() && str.charAt(0) == '"') {
			ret = true;
		}
		return ret;
	}

	public static boolean isNumber(String str) {
		boolean ret = false;
		if (str.matches("-?[0-9]+(.[0-9]+)?")) {
			ret = true;
		}
		return ret;
	}

	public static boolean isBool(String str) {
		boolean ret = false;
		if (str.equals("true") || str.equals("false")) {
			ret = true;
		}
		return ret;
	}

	public Type getType() {
		return type;
	}

	public static boolean canBeName(String str) {
		return str.matches("^[a-zA-z][a-z_A-z0-9]*$");
	}

	public boolean isNone() {
		return this.getType() == Type.None;
	}

	public boolean isWord() {
		return this.getType() == Type.Word;
	}

	public boolean isNumber() {
		return this.getType() == Type.Number;
	}

	public boolean isBool() {
		return this.getType() == Type.Bool;
	}

	public boolean canBeName() {
		boolean ret = false;
		if (isWord()) {
			ret = MuaData.canBeName(word);
		}
		return ret;
	}

	public String getWord() {
		return this.word;
	}

	public double getNumber() {
		return this.number;
	}

	public boolean getBool() {
		return this.bool;
	}

	public MuaData toWord() {
		MuaData ret = new MuaData();
		if (this.isWord()) {
			ret = this;
		} else if (this.isNumber()) {
			ret = new MuaData(String.valueOf(this.getNumber()));
		} else if (this.isBool()) {
			ret = new MuaData(String.valueOf(this.getBool()));
		}
		return ret;
	}

	public MuaData toNumber() {
		MuaData ret = new MuaData();
		if (this.isWord() && MuaData.isNumber(this.getWord())) {
			ret = new MuaData(Double.parseDouble(this.getWord()));
		} else if (this.isNumber()) {
			ret = this;
		} else if (this.isBool()) {
			ret = new MuaData(this.getBool() ? 1 : 0);
		}
		return ret;
	}

	public MuaData toBool() {
		MuaData ret = new MuaData();
		if (this.isWord() && (this.getWord().equals("true") || this.getWord().equals("false"))) {
			ret = new MuaData(Boolean.parseBoolean(this.getWord()));
		} else if (this.isNumber()) {
			ret = new MuaData(this.getNumber() != 0);
		} else if (this.isBool()) {
			ret = this;
		}
		return ret;
	}

	public boolean isCalculable() {
		return this.isNumber() || this.isBool() || isNumber(this.word) ;
	}

	public void print() {
		if (isWord()) {
			System.out.print(word);
		} else if (isNumber()) {
			System.out.print(number);
		} else if (isBool()) {
			System.out.print(bool);
		}
	}

	public MuaData arithmeticOperation(MuaData that, char op) {
		MuaData ret = new MuaData();
		MuaData operand1 = this.toNumber();
		MuaData operand2 = that.toNumber();
		if (operand1.isNumber() && operand2.isNumber()) {
			if (op == '+') {
				ret = new MuaData(operand1.getNumber() + operand2.getNumber());
			} else if (op == '-') {
				ret = new MuaData(operand1.getNumber() - operand2.getNumber());
			} else if (op == '*') {
				ret = new MuaData(operand1.getNumber() * operand2.getNumber());
			} else if (op == '/') {
				// TODO: error /0
				ret = new MuaData(operand1.getNumber() / operand2.getNumber());
			} else if (op == '%') {
				// TODO: error %0
				ret = new MuaData(operand1.getNumber() % operand2.getNumber());
			}
		}
		return ret;
	}

	public MuaData logicOperation(MuaData that, char op) {
		MuaData ret = new MuaData();
		MuaData operand1 = this.toBool();
		MuaData operand2 = that.toBool();
		if (operand1.isBool() && operand2.isBool()) {
			if (op == '&') {
				ret = new MuaData(operand1.getBool() && operand2.getBool());
			} else if (op == '|') {
				ret = new MuaData(operand1.getBool() || operand2.getBool());
			} else if (op == '^') {
				ret = new MuaData(operand1.getBool() ^ operand2.getBool());
			}
		}
		return ret;
	}

	public MuaData logicOperation(char op) {
		MuaData ret = new MuaData();
		if (this.isBool()) {
			if (op == '!') {
				ret = new MuaData(!this.getBool());
			}
		}
		return ret;
	}

	public MuaData compare(MuaData that, char op) {
		MuaData ret = new MuaData(false);
		double cmpRes = 0;
		boolean flag = false;
		if (this.isNumber() && that.isNumber()) {
			cmpRes = this.getNumber() - that.getNumber();
			flag = true;
		} else if (this.isWord() && that.isWord()) {
			cmpRes = this.getWord().compareTo(that.getWord());
			flag = true;
		} else if (!this.isBool() && !that.isBool()) {
			MuaData operand1 = this.toNumber();
			MuaData operand2 = that.toNumber();
			if (operand1.isNumber() && operand2.isNumber()) {
				cmpRes = operand1.getNumber() - operand2.getNumber();
				flag = true;
			}
		}
		if (flag) {
			if (op == '=') {
				ret = new MuaData(cmpRes == 0);
			} else if (op == '<') {
				ret = new MuaData(cmpRes < 0);
			} else if (op == '>') {
				ret = new MuaData(cmpRes > 0);
			}
		}
		return ret;
	}
}
