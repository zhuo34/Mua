package src.mua.MuaOperation;

import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaNumber;
import src.mua.MuaValue.MuaValue;

import java.util.ArrayList;

public class Wait implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		int ms = (int) MuaNumber.convertFrom(args.get(0)).getNumber();
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		return new MuaNone();
	}
}
