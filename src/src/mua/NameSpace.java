package src.mua;

import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaNumber;
import src.mua.MuaValue.MuaValue;

import java.util.HashMap;
import java.util.Set;

public class NameSpace {

	private HashMap<String, MuaValue> mNameMap = new HashMap<String, MuaValue>();
	public MuaValue output = new MuaNone();

	public NameSpace() {
		make("pi", new MuaNumber(3.14159));
		make("run", new MuaNumber(3.14159));
	}

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

	public Set<String> getKeys() {
		return mNameMap.keySet();
	}

}
