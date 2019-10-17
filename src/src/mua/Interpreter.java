package src.mua;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	private NameSpace globalNameSpace = new NameSpace();
	private MuaStack globalStack = new MuaStack(globalNameSpace);

	public static Scanner ioScanner = new Scanner(System.in);

	public Interpreter() {
//		globalNameSpace = new NameSpace();
//		globalStack = new MuaStack(globalNameSpace);
	}

	public void parse() {
//		System.out.print("> ");
		while (ioScanner.hasNextLine()) {
			String str = ioScanner.nextLine();
			parseLine(str);
//			System.out.print("> ");
		};
	}

	public void parse(String filename) {
		Path filePath = Paths.get(filename);
		try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				parseLine(str);
			};
		} catch (IOException e) {
			System.out.println("Open '" + filename + "' failed.");
		}
		parse();
	}

	private void parseLine(String line) {
		// remove comments
		int commentIndex = line.indexOf("//");
		if (commentIndex != -1) {
			line = line.substring(0, commentIndex);
		}

		// convert : to thing
		line = line.replace(":", "thing \"");
//		System.out.println(line);
		ArrayList<MuaItem> ret = new ArrayList<MuaItem>();
		Scanner scanner = new Scanner(line);
		while (scanner.hasNext()) {
			String str = scanner.next();
			ret.add(MuaItem.parseLiteral(str));
		};
		processStatement(ret);
	}

	private void processStatement(ArrayList<MuaItem> statement) {
		globalStack.processStatement(statement);
	}
}
