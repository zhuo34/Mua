package src.mua.Operations;

import src.mua.MuaItem;
import src.mua.MuaStack;
import src.mua.Value.Value;

import java.util.ArrayList;

public interface Operation extends MuaItem {
	public int argNumber();
	public Value execute(ArrayList<Value> args, MuaStack caller);
}
