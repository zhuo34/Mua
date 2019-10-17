package src.mua.Value;

public class Number implements Value {

	private double number = 0;

	public Number(double number) {
		this.number = number;
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
		return new Bool(this.number != 0);
	}

	@Override
	public Value toWord() {
		return new Word(String.valueOf(this.getNumber()));
	}

	@Override
	public double getNumber() {
		return this.number;
	}

	@Override
	public boolean getBool() {
		return false;
	}

	@Override
	public String getWord() {
		return null;
	}

	@Override
	public void print() {
		System.out.print(this.getNumber());
	}
}
