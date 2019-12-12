package src.mua.MuaOperation;

import src.mua.MuaItem;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public interface MuaOperation extends MuaItem {
	public int argNumber();
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller);
}
