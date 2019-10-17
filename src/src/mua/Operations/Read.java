package src.mua.Operations;

import src.mua.Interpreter;
import src.mua.NameSpace;
import src.mua.Value.None;
import src.mua.Value.Value;
import src.mua.Value.Word;

import java.util.ArrayList;

public class Read implements Operation {
	@Override
	public int argNumber() {
		return 0;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = new None();
		boolean flag = true;
//		Scanner scanner = new Scanner(System.in);
		if (Interpreter.ioScanner.hasNext()) {
			ret = new Word(Interpreter.ioScanner.next());
		}
		return ret;
	}
}
