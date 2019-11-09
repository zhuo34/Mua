package src.mua;

import src.mua.Operations.OperationFactory;
import src.mua.Value.List;
import src.mua.Value.None;
import src.mua.Value.Value;
import src.mua.Value.ValueFactory;

import java.util.ArrayList;

public class MuaItemFactory {

	public static ArrayList<MuaItem> parseLiteral(String str) {
		ArrayList<MuaItem> ret = new ArrayList<>();
		if (ValueFactory.isParsingList()) {
			Value v = ValueFactory.parseLiteral(str);
			if (!(v instanceof None)) {
				ret.add(v);
			}
		} else {
			if (str.charAt(0) == ':') {
				ret.add(OperationFactory.getOperation("thing"));
				Value v = ValueFactory.parseLiteral("\"" + str.substring(1));
				if (!(v instanceof None)) {
					ret.add(v);
				}
			} else if (OperationFactory.isOperation(str)) {
				ret.add(OperationFactory.getOperation(str));
			} else {
				Value v = ValueFactory.parseLiteral(str);
				if (!(v instanceof None)) {
					ret.add(v);
				}
			}
		}

		return ret;
	}

}
