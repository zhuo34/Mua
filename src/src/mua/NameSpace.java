package src.mua;

import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;

import java.util.HashMap;

public class NameSpace {

	private HashMap<String, MuaValue> mNameMap = new HashMap<String, MuaValue>();
	public MuaValue output = new MuaNone();

	public NameSpace() {}

	public void make(String name, MuaValue data) {
		mNameMap.put(name, data);
	}

	public void erase(String name) {
		mNameMap.remove(name);
	}

	public MuaValue get(String name) {
		return mNameMap.get(name);
	}

	public Boolean has(String name) {
		return mNameMap.containsKey(name);
	}

	public void clear() {
		mNameMap.clear();
	}

	public void exportTo(NameSpace ns) {
		for (HashMap.Entry<String, MuaValue> entry: mNameMap.entrySet()){
			ns.make(entry.getKey(), entry.getValue());
		}
	}

}
