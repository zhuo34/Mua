package src.mua.Operations;

import src.mua.MuaItem;
import src.mua.NameSpace;
import src.mua.Value.Value;

import java.util.ArrayList;

public interface Operation extends MuaItem {
	public int argNumber();
	public Value execute(ArrayList<Value> args, NameSpace ns);
}
