package src.mua.Value;

public class Bool implements Value {

	private boolean bool = false;

	public Bool(boolean bool) {
		this.bool = bool;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public Value toNumber() {
		return new Number(this.bool ? 1.0 : 0.0);
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
	public double getNumber() {
		return 0;
	}

	@Override
	public boolean getBool() {
		return this.bool;
	}

	@Override
	public String getWord() {
		return null;
	}

	@Override
	public void print() {
		System.out.print(this.getBool());
	}
}
