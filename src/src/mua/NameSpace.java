package src.mua;

import src.mua.Value.Value;

import java.util.HashMap;

public class NameSpace {

	private HashMap<String, Value> nameMap = new HashMap<String, Value>();

	public NameSpace() {}

	public void make(String name, Value data) {
		nameMap.put(name, data);
	}

	public void erase(String name) {
		nameMap.remove(name);
	}

	public Value get(String name) {
		return nameMap.get(name);
	}

	public Boolean has(String name) {
		return this.nameMap.containsKey(name);
	}

}
