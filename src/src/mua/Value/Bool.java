package src.mua.Value;

import java.util.ArrayList;

public class Bool implements Value {

	private boolean mBool = false;

	public Bool(boolean bool) {
		this.mBool = bool;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public Value toNumber() {
		return new Number(this.mBool ? 1.0 : 0.0);
	}

	@Override
	public Value toBool() {
		return this;
	}

	@Override
	public Value toWord() {
		return new Word(String.valueOf(this.getBool()));
	}

	@Override
	public Value toList() {
		return new None();
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
	public ArrayList<Value> getList() {
		return this.toList().getList();
	}

	@Override
	public void print() {
		System.out.print(this.getBool());
	}
}
