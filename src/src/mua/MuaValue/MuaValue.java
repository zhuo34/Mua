package src.mua.MuaValue;

import src.mua.MuaItem;

import java.util.ArrayList;

public interface MuaValue extends MuaItem {
	public boolean canBeName();
	public MuaValue toNumber();
	public MuaValue toBool();
	public MuaValue toWord();
	public MuaValue toList();
	public double getNumber();
	public boolean getBool();
	public String getWord();
	public ArrayList<MuaValue> getList();
	public void print();
}
