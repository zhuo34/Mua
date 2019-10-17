package src.mua.Value;

public class None implements Value {
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
		return this;
	}

	@Override
	public Value toWord() {
		return this;
	}

	@Override
	public double getNumber() {
		return 0;
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
		
	}
}
