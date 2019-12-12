package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.util.ArrayList;

public class MuaBool implements MuaValue {

	private boolean mBool = false;

	public MuaBool(boolean bool) {
		this.mBool = bool;
	}

	public MuaNumber toNumber() {
		return new MuaNumber(this.mBool ? 1 : 0);
	}

	public MuaWord toWord() {
		return new MuaWord(String.valueOf(this.getBool()));
	}

	public boolean getBool() {
		return this.mBool;
	}

	@Override
	public String value() {
		return String.valueOf(this.mBool);
	}

	public static MuaBool convertFrom(MuaValue v) {
		if (v instanceof MuaBool) {
			return (MuaBool) v;
		} else if (v instanceof MuaWord) {
			return convertFrom((MuaWord) v);
		} else if (v instanceof MuaNumber) {
			return convertFrom((MuaNumber) v);
		} else {
			throw new MuaException("convert to bool.");
		}
	}

	public static MuaBool convertFrom(MuaWord word) {
		return word.toBool();
	}

	public static MuaBool convertFrom(MuaNumber num) {
		return num.toBool();
	}
}
