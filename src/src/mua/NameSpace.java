package src.mua;

import src.mua.Value.Value;

import java.util.HashMap;

public class NameSpace {

	private HashMap<String, Value> mNameMap = new HashMap<String, Value>();

	public NameSpace() {}

	public void make(String name, Value data) {
		mNameMap.put(name, data);
	}

	public void erase(String name) {
		mNameMap.remove(name);
	}

	public Value get(String name) {
		return mNameMap.get(name);
	}

	public Boolean has(String name) {
		return mNameMap.containsKey(name);
	}

	public void clear() {
		mNameMap.clear();
	}

}
