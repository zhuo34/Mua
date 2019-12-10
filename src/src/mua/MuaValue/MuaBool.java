package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaBool implements MuaValue {

	private boolean mBool = false;

	public MuaBool(boolean bool) {
		this.mBool = bool;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public MuaValue toNumber() {
		return new MuaNumber(this.mBool ? 1.0 : 0.0);
	}

	@Override
	public MuaValue toBool() {
		return this;
	}

	@Override
	public MuaValue toWord() {
		return new MuaWord(String.valueOf(this.getBool()));
	}

	@Override
	public MuaValue toList() {
		return new MuaNone();
	}

	@Override
	public double getNumber() {
		return this.toNumber().getNumber();
	}

	@Override
	public boolean getBool() {
		return this.mBool;
	}

	@Override
	public String getWord() {
		return this.toWord().getWord();
	}

	@Override
	public ArrayList<MuaValue> getList() {
		return this.toList().getList();
	}

	@Override
	public void print() {
		System.out.print(this.getBool());
	}
}
