package src.mua.Value;

import java.util.ArrayList;

public class Number implements Value {

	private double mNumber = 0;

	public Number(double number) {
		this.mNumber = number;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public Value toNumber() {
		return this;
	}

	@Override
	public Value toBool() {
		return new Bool(this.mNumber != 0);
	}

	@Override
	public Value toWord() {
		return new Word(String.valueOf(this.getNumber()));
	}

	@Override
	public Value toList() {
		return new None();
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
	public ArrayList<Value> getList() {
		return this.toList().getList();
	}

	@Override
	public void print() {
		System.out.print(this.getNumber());
	}
}
