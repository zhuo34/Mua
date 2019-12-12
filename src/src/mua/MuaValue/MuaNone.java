package src.mua.MuaValue;

import java.util.ArrayList;

public class MuaNone implements MuaValue {

	public enum NoneInfo{
		None, Stop
	}

	public NoneInfo info = NoneInfo.None;

	@Override
	public String value() {
		return "";
	}

}
