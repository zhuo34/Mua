package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNumber;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Sqrt implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		double num = MuaNumber.convertFrom(args.get(0)).getNumber();
		return new MuaNumber(Math.sqrt(num));
	}
}
