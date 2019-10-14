package src.mua;

import java.util.HashMap;

public class NameSpace {

	private HashMap<String, MuaData> nameMap = new HashMap<String, MuaData>();

	public NameSpace() {}

	public void make(String name, MuaData data) {
		nameMap.put(name, data);
	}

	public void erase(String name) {
		nameMap.remove(name);
	}

	public MuaData get(String name) {
		return nameMap.get(name);
	}

	public Boolean has(String name) {
		return this.nameMap.containsKey(name);
	}

}
