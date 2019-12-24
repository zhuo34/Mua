package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MuaList implements MuaValue {
	public static final char prefix = '[';
	public static final char postfix = ']';

	private ArrayList<MuaValue> mList = new ArrayList<>();

	public MuaList() {
	}

	public MuaList(MuaValue v) {
		this.mList.add(v);
	}

	public MuaList(ArrayList<MuaValue> list) {
		this.mList = list;
	}

	@Override
	public String value() {
		return this.contentToString();
	}

	public String contentToString() {
		StringBuilder sb = new StringBuilder();
		if (this.mList.isEmpty()) {
			sb.append(" ");
		} else {
			sb.append(MuaWord.convertFrom(mList.get(0)).getWord());
			for (int i = 1; i < mList.size(); i++) {
				sb.append(" ").append(MuaWord.convertFrom(mList.get(i)).getWord());
			}
		}
		return sb.toString();
	}

	protected String listToString(String prefix, String postfix) {
		return prefix + this.contentToString() + postfix;
	}

	public String listToString() {
		return this.listToString(String.valueOf(prefix), String.valueOf(postfix));
	}

	public void add(MuaValue v) {
		this.mList.add(v);
	}

	public MuaWord toWord() {
		return new MuaWord(this.listToString());
	}

	public ArrayList<MuaValue> getList() {
		ArrayList<MuaValue> ret = new ArrayList<>();
		return this.mList;
	}

	public static MuaList convertFrom(MuaValue v) {
		if (v instanceof MuaList) {
			return (MuaList) v;
		} else {
			throw new MuaException("convert to list.");
		}
	}

	public int size() {
		return this.mList.size();
	}

	public boolean isEmpty() {
		return this.mList.isEmpty();
	}

	public MuaValue get(int index) {
		return this.mList.get(index);
	}

	public ArrayList<MuaValue> subList(int fromIndex) {
		return new ArrayList<>(this.mList.subList(fromIndex, this.size()));
	}

	public ArrayList<MuaValue> subList(int fromIndex, int toIndex) {
		return new ArrayList<>(this.mList.subList(fromIndex, toIndex));
	}

	public interface MuaListInstance {
		MuaList newInstance();
	}
}
