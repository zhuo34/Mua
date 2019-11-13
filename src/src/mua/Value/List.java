package src.mua.Value;

import java.util.ArrayList;

public class List implements Value {

	private ArrayList<Value> mList = new ArrayList<>();

	public List() {
	}

	public List(Value v) {
		this.add(v);
	}

	public List(ArrayList<Value> list) {
		this.mList = list;
	}

	@Override
	public boolean canBeName() {
		return false;
	}

	@Override
	public Value toNumber() {
		return new None();
	}

	@Override
	public Value toBool() {
		return new None();
	}

	@Override
	public Value toWord() {
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
		return new Word(sb.toString());
	}

	@Override
	public Value toList() {
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
	public ArrayList<Value> getList() {
		return this.mList;
	}

	@Override
	public void print() {
		this.toWord().print();
	}

	public void add(Value v) {
		this.mList.add(v);
	}
}
