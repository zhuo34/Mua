package src.mua;

import src.mua.Exception.MuaException;
import src.mua.MuaOperation.MuaOperationFactory;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaValueFactory;

import java.util.ArrayList;

public class MuaItemFactory {

	public static ArrayList<MuaItem> parseLiteral(String str) throws MuaException {
		ArrayList<MuaItem> ret = new ArrayList<>();
		MuaValue v = new MuaNone();
		if (MuaValueFactory.isParsing()) {
			v = MuaValueFactory.parseLiteral(str);
		} else {
			if (str.charAt(0) == ':') {
				ret.add(MuaOperationFactory.getOperation("thing"));
				v = MuaValueFactory.parseLiteral("\"" + str.substring(1));
			} else if (MuaOperationFactory.isOperation(str)) {
				ret.add(MuaOperationFactory.getOperation(str));
			} else {
				v = MuaValueFactory.parseLiteral(str);
			}
		}
		if (!(v instanceof MuaNone)) {
			ret.add(v);
		}
		return ret;
	}
}
