package src.mua.Exception;

public class MuaException extends RuntimeException {
	private String message;
	private String prefix;

	public MuaException(String message) {
		this.message = message;
	}

	public String what() {
		return message;
	}

	public void print() {
		System.out.println(this.what());
	}
}
