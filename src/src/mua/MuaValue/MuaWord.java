package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaWord implements MuaValue {

	private String mWord = "";

	public MuaWord(String word) {
		this.mWord = word;
	}

	@Override
	public boolean canBeName() {
		return this.mWord.matches("^[a-zA-z][a-z_A-z0-9]*$");
	}

	@Override
	public MuaValue toNumber() {
		MuaValue ret = new MuaNone();
		if (MuaValueFactory.literalIsNumber(this.getWord())) {
			ret = new MuaNumber(Double.parseDouble(this.getWord()));
		}
		return ret;
	}

	@Override
	public MuaValue toBool() {
		MuaValue ret = new MuaNone();
		if ((this.getWord().equals("true") || this.getWord().equals("false"))) {
			ret = new MuaBool(Boolean.parseBoolean(this.getWord()));
		}
		return ret;
	}

	@Override
	public MuaValue toWord() {
		return this;
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
		return this.toBool().getBool();
	}

	@Override
	public String getWord() {
		return mWord;
	}

	@Override
	public ArrayList<MuaValue> getList() {
		return this.toList().getList();
	}

	@Override
	public void print() {
		System.out.print(this.getWord());
	}
}
