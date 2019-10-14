package src.mua.Operations;

import src.mua.MuaData;
import src.mua.NameSpace;

import java.util.ArrayList;

public interface Operation {
	public int argNumber();
	public MuaData execute(ArrayList<MuaData> args, NameSpace ns);
}
