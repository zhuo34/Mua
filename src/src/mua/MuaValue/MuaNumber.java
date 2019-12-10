package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaNumber implements MuaValue {

	private double mNumber = 0;

	public MuaNumber(double number) {
		this.mNumber = number;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public MuaValue toNumber() {
		return this;
	}

	@Override
	public MuaValue toBool() {
		return new MuaBool(this.mNumber != 0);
	}

	@Override
	public MuaValue toWord() {
		return new MuaWord(String.valueOf(this.getNumber()));
	}

	@Override
	public MuaValue toList() {
		return new MuaNone();
	}

	@Override
	public double getNumber() {
		return this.mNumber;
	}

	@Override
	public boolean getBool() {
		return this.toBool().getBool();
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
		System.out.print(this.getNumber());
	}
}
