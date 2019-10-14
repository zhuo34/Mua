package src.mua;

public class MuaItem {

	public enum Type {
		None, Op, Data
	}

	private Type type = Type.None;
	private String op = "";
	private MuaData data;

	public MuaItem() {}

	public MuaItem(String op) {
		type = Type.Op;
		this.op = op;
	}

	public MuaItem(MuaData data) {
		type = Type.Data;
		this.data = data;
	}

	public static MuaItem parseLiteral(String str) {
		MuaItem ret = new MuaItem();
		if (!str.isEmpty()) {
			char firstChar = str.charAt(0);
			if (MuaData.isMuaData(str)) {
				ret.type = Type.Data;
				ret.data = MuaData.parseLiteral(str);
			} else if (MuaData.canBeName(str)) {
				ret.type = Type.Op;
				ret.op = str;
			} else {
				// TODO: error
			}
		}
		return ret;
	}

	public Type getType() {
//		if (isData()) {
//			System.out.println(data.getType());
//		}
		return type;
	}

	public boolean isOp() {
		return type == Type.Op;
	}

	public boolean isData() {
		return type == Type.Data;
	}

	public String getOp() {
		return op;
	}

	public MuaData getData() {
		return data;
	}

}
