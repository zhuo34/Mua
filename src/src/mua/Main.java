package src.mua;


public class Main {

    public static void main(String[] args) {

		Interpreter interpreter = new Interpreter();
//        System.out.println("Welcome to MUA");
//        if (args.length == 0) {
            interpreter.parse();
//        } else {
//            interpreter.parse(args[args.length - 1]);
//        }
    }
}
