package src.mua.Value;

import src.mua.MuaItem;

import java.util.ArrayList;

public interface Value extends MuaItem {
	public boolean canBeName();
	public Value toNumber();
	public Value toBool();
	public Value toWord();
	public Value toList();
	public double getNumber();
	public boolean getBool();
	public String getWord();
	public ArrayList<Value> getList();
	public void print();
}
