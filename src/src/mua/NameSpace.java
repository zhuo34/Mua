package src.mua;

import src.mua.MuaValue.*;

import java.util.ArrayList;
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

	public void bind(ArrayList<String> names, ArrayList<MuaValue> args) {
		this.clear();
		for (int i = 0; i < names.size(); i++) {
			this.make(names.get(i), args.get(i));
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (HashMap.Entry<String, MuaValue> entry: mNameMap.entrySet()){
			sb.append("make \"").append(entry.getKey()).append(" ");
			MuaValue v = entry.getValue();
			if (v instanceof MuaWord) {
				sb.append("\"").append(v.value());
			} else if (v instanceof MuaList) {
				sb.append(MuaList.prefix).append(v.value()).append( MuaList.postfix);
			} else {
				sb.append(v.value());
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
