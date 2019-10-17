package src.mua.Value;

import src.mua.MuaItem;

public interface Value extends MuaItem {
	public boolean canBeName();
	public Value toNumber();
	public Value toBool();
	public Value toWord();
	public double getNumber();
	public boolean getBool();
	public String getWord();
	public void print();
}
