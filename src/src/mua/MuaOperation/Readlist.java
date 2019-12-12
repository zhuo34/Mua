package src.mua.MuaOperation;

import src.mua.Interpreter;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaList;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.util.ArrayList;
import java.util.Scanner;

public class Readlist implements MuaOperation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		MuaList ret = new MuaList();
		boolean flag = true;
		Scanner s = caller.scanner;
		if (!caller.scanner.hasNextLine()) {
			s = Interpreter.ioScanner;
		}
		String line = s.nextLine();
		Scanner scanner = new Scanner(line);
		while (scanner.hasNext()) {
			String str = scanner.next();
			ret.add(new MuaWord(str));
		};
		return ret;
	}
}
