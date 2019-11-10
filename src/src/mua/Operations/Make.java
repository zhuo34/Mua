package src.mua.Operations;

import src.mua.NameSpace;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;

import java.util.ArrayList;

public class Make implements Operation {
	@Override
	public int argNumber() {
		return 2;
	}

	@Override
	public Value execute(ArrayList<Value> args, NameSpace ns) {
		Value ret = new None();
		Value name = args.get(0);
		Value v = args.get(1);
		if (name.canBeName()) {
			OperationFactory.eraseFunction(name.getWord());
			if ((v instanceof List) && v.getList().size() == 2) {
				Value argList = v.getList().get(0);
				Value funcBody = v.getList().get(1);
				if ((argList instanceof List) && (funcBody instanceof List)) {
					OperationFactory.addFunction(name.getWord(), (List)argList, (List)funcBody);
				}
			}
			ns.make(args.get(0).getWord(), args.get(1));
		}
		return ret;
	}
}
