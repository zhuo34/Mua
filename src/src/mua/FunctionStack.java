package src.mua;

public class FunctionStack extends MuaStack {
	public FunctionStack(NameSpace ns) {
		super(ns);
		this.isFunction = true;
	}
}
