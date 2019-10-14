package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.MuaData;
import src.mua.NameSpace;

import java.util.ArrayList;
import java.util.Scanner;

public class Read implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaData execute(ArrayList<MuaData> args, NameSpace ns) {
		MuaData ret = new MuaData();
		boolean flag = true;
//		Scanner scanner = new Scanner(System.in);
		if (Interpreter.ioScanner.hasNext()) {
			ret = new MuaData(Interpreter.ioScanner.next());
		}
		return ret;
	}
}
