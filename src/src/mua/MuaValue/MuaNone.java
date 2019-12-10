package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaNone implements MuaValue {

	public enum NoneInfo{
		None, Stop
	}

	public NoneInfo info = NoneInfo.None;

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
		return this;
	}

	@Override
	public MuaValue toWord() {
		return this;
	}

	@Override
	public MuaValue toList() {
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
	public ArrayList<MuaValue> getList() {
		return null;
	}

	@Override
	public void print() {
		
	}

}
