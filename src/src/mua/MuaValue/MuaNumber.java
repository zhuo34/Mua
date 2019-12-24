package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.util.ArrayList;

public class MuaNumber implements MuaValue {

	private double mNumber = 0;

	public MuaNumber(double number) {
		this.mNumber = number;
	}

	public MuaBool toBool() {
		return new MuaBool(this.mNumber != 0);
	}

	public MuaWord toWord() {
		return new MuaWord(String.valueOf(this.getNumber()));
	}

	public double getNumber() {
		return this.mNumber;
	}

	@Override
	public String value() {
		return String.valueOf(this.mNumber);
	}

	public static MuaNumber convertFrom(MuaValue v) {
		if (v instanceof MuaWord) {
			return convertFrom((MuaWord) v);
		} else if (v instanceof MuaNumber) {
			return (MuaNumber) v;
		} else if (v instanceof MuaBool) {
			return convertFrom((MuaBool) v);
		} else if (v instanceof MuaExpression) {
			return convertFrom((MuaExpression) v);
		} else {
			throw new MuaException(v.value() + "convert to number.");
		}
	}

	public static MuaNumber convertFrom(MuaWord word) {
		return word.toNumber();
	}

	public static MuaNumber convertFrom(MuaBool bool) {
		return bool.toNumber();
	}

	public static MuaNumber convertFrom(MuaExpression exp) {
		return exp.toNumber();
	}

	public MuaNumber opposite() {
		this.mNumber = -this.mNumber;
		return this;
	}
}
