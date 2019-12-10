package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaList implements MuaValue {

	private ArrayList<MuaValue> mList = new ArrayList<>();

	public MuaList() {
	}

	public MuaList(MuaValue v) {
		this.add(v);
	}

	public MuaList(ArrayList<MuaValue> list) {
		this.mList = list;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public MuaValue toNumber() {
		return new MuaNone();
	}

	@Override
	public MuaValue toBool() {
		return new MuaNone();
	}

	@Override
	public MuaValue toWord() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		if (this.mList.isEmpty()) {
			sb.append(" ");
		} else {
			sb.append(mList.get(0).toWord().getWord());
			for (int i = 1; i < mList.size(); i++) {
				sb.append(" ").append(mList.get(i).toWord().getWord());
			}
		}
		sb.append("]");
		return new MuaWord(sb.toString());
	}

	@Override
	public MuaValue toList() {
		return this;
	}

	@Override
	public double getNumber() {
		return this.toNumber().getNumber();
	}

	@Override
	public boolean getBool() {
		return this.toBool().getBool();
	}

	@Override
	public String getWord() {
		String str = this.toWord().getWord();
		return str.substring(1, str.length() - 1);
	}

	@Override
	public ArrayList<MuaValue> getList() {
		return this.mList;
	}

	@Override
	public void print() {
		this.toWord().print();
	}

	public void add(MuaValue v) {
		this.mList.add(v);
	}
}
