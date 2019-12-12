package src.mua.MuaValue;

import src.mua.Exception.MuaException;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MuaList implements MuaValue {
	public static final char prefix = '[';
	public static final char postfix = ']';

	ArrayList<MuaValue> mList = new ArrayList<>();

	public MuaList() {
	}

	public MuaList(MuaValue v) {
		this.mList.add(v);
	}

	public MuaList(ArrayList<MuaValue> list) {
		this.mList = list;
	}

	public MuaWord toWord() {
		return new MuaWord(listToString());
	}

	public String contentToString () {
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

	public String listToString () {
		return prefix + contentToString() + postfix;
	}

	public ArrayList<MuaValue> getList() {
		return this.mList;
	}

	public static MuaList convertFrom(MuaValue v) throws MuaException {
		if (v instanceof MuaList) {
			return (MuaList) v;
		} else {
			throw new MuaException("convert to list.");
		}
	}

	@Override
	public String value() {
		return listToString();
	}

	public void add(MuaValue v) throws MuaException {
		this.mList.add(v);
	}

	public int size(){
		return this.mList.size();
	}

	public MuaValue get(int index){
		return this.mList.get(index);
	}

	public ArrayList<MuaValue> subList(int fromIndex){
		return new ArrayList<>(this.mList.subList(fromIndex, this.size()));
	}

	public ArrayList<MuaValue> subList(int fromIndex, int toIndex){
		return new ArrayList<>(this.mList.subList(fromIndex, toIndex));
	}

	public interface MuaListInstance {
		MuaList newInstance();
	}
}
