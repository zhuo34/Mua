package src.mua;

import src.mua.Operations.OperationFactory;
import src.mua.Value.ValueFactory;

public interface MuaItem {

//	public enum Type {
//		None, Op, Data
//	}


	public static MuaItem parseLiteral(String str) {
		MuaItem ret;
		if (OperationFactory.isOperation(str)) {
			ret = OperationFactory.getOperation(str);
		} else {
			ret = ValueFactory.parseLiteral(str);
		}
		return ret;
	}

}
