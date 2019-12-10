package src.mua;

import src.mua.Operations.OperationFactory;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaValueFactory;

import java.util.ArrayList;

public class MuaItemFactory {

	public static ArrayList<MuaItem> parseLiteral(String str) {
		ArrayList<MuaItem> ret = new ArrayList<>();
		if (MuaValueFactory.isParsingList()) {
			MuaValue v = MuaValueFactory.parseLiteral(str);
			if (!(v instanceof MuaNone)) {
				ret.add(v);
			}
		} else {
			if (str.charAt(0) == ':') {
				ret.add(OperationFactory.getOperation("thing"));
				MuaValue v = MuaValueFactory.parseLiteral("\"" + str.substring(1));
				if (!(v instanceof MuaNone)) {
					ret.add(v);
				}
			} else if (OperationFactory.isOperation(str)) {
				ret.add(OperationFactory.getOperation(str));
			} else {
				MuaValue v = MuaValueFactory.parseLiteral(str);
				if (!(v instanceof MuaNone)) {
					ret.add(v);
				}
			}
		}

		return ret;
	}

}
