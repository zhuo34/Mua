package src.mua.Value;

import java.util.ArrayList;

public class List implements Value {

	ArrayList<Value> list = new ArrayList<>();

	public List() {
	}

	public List(Value v) {
		this.add(v);
	}

	public List(ArrayList<Value> list) {
		this.list = list;
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
		if (this.list.isEmpty()) {
			sb.append(" ");
		} else {
			sb.append(list.get(0).toWord().getWord());
			for (int i = 1; i < list.size(); i++) {
				sb.append(" ").append(list.get(i).toWord().getWord());
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
		return 0;
	}

	@Override
	public boolean getBool() {
		return false;
	}

	@Override
	public String getWord() {
		return null;
	}

	@Override
	public ArrayList<Value> getList() {
		return this.list;
	}

	@Override
	public void print() {
		this.toWord().print();
	}

	public void add(Value v) {
		this.list.add(v);
	}
}
