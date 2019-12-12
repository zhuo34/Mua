package src.mua;


import src.mua.Exception.MuaException;

public class Main {

	public static Interpreter interpreter = new Interpreter();

    public static void main(String[] args) {
//        System.out.println("Welcome to MUA");
//        if (args.length == 0) {
            interpreter.parse();
//        } else {
//            interpreter.parse(args[args.length - 1]);
//        }
//		try {
//			throw new MuaException("sdf");
//		} catch (MuaException e) {
//			System.out.println(e.what());
//		}
    }
}
