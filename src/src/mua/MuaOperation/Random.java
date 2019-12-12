package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNumber;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Random implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		int max = (int) MuaNumber.convertFrom(args.get(0)).getNumber();
		int random = (int) (Math.random() * max);
		return new MuaNumber(random);
	}
}
