package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.util.ArrayList;

public class MuaWord implements MuaValue {

	private String mWord = "";

	public MuaWord(char c) {
		this.mWord = String.valueOf(c);
	}

	public MuaWord(String word) {
		this.mWord = word;
	}

	public boolean canBeName() {
		return this.mWord.matches("^[a-zA-z][a-z_A-z0-9]*$");
	}

	public MuaNumber toNumber() {
		if (MuaValueFactory.literalIsNumber(this.mWord)) {
			return new MuaNumber(Double.parseDouble(this.mWord));
		} else {
			throw new MuaException("convert to number.");
		}
	}

	public MuaBool toBool() {
		if ((this.mWord.equals("true") || this.mWord.equals("false"))) {
			return new MuaBool(Boolean.parseBoolean(this.mWord));
		} else {
			throw new MuaException("convert to bool.");
		}
	}

	public String getWord() {
		return mWord;
	}

	@Override
	public String value() {
		return this.mWord;
	}

	public static MuaWord convertFrom(MuaValue v) {
		if (v instanceof MuaWord) {
			return (MuaWord) v;
		} else if (v instanceof MuaNumber) {
			return convertFrom((MuaNumber) v);
		} else if (v instanceof MuaBool) {
			return convertFrom((MuaBool) v);
		} else if (v instanceof MuaList) {
			return convertFrom((MuaList) v);
		} else {
			throw new MuaException("convert to word.");
		}
	}

	public static MuaWord convertFrom(MuaNumber num) {
		return num.toWord();
	}

	public static MuaWord convertFrom(MuaBool bool) {
		return bool.toWord();
	}

	public static MuaWord convertFrom(MuaList list) {
		return list.toWord();
	}

	public static MuaWord concatenate(MuaWord w0, MuaWord w1) {
		return new MuaWord(w0.getWord() + w1.getWord());
	}

	public int length() {
		return this.mWord.length();
	}

	public char charAt(int index) {
		return this.mWord.charAt(index);
	}

	public String subString(int beginIndex) {
		return this.mWord.substring(beginIndex);
	}

	public String subString(int beginIndex, int endIndex) {
		return this.mWord.substring(beginIndex, endIndex);
	}
}
