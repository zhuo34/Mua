package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.MuaItem;
import src.mua.MuaStack;
import src.mua.Value.List;
import src.mua.Value.Value;
import src.mua.Value.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class Readlist implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public Value execute(ArrayList<Value> args, MuaStack caller) {
		List ret = new List();
		boolean flag = true;
		if (Interpreter.ioScanner.hasNextLine()) {
			String line = Interpreter.ioScanner.nextLine();
			Scanner scanner = new Scanner(line);
			while (scanner.hasNext()) {
				String str = scanner.next();
				ret.add(new Word(str));
			};
		}
		return ret;
	}
}
