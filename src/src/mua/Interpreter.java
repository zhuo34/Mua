package src.mua;

import src.mua.Value.None;
import src.mua.Value.ValueFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Interpreter {

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	public static NameSpace globalNameSpace = new NameSpace();
	public static MuaStack globalStack = new MuaStack(globalNameSpace);

	public static Scanner ioScanner = new Scanner(System.in);

	public Interpreter() {}

	public void parse() {
//		System.out.print("> ");
		while (ioScanner.hasNextLine()) {
			String line = ioScanner.nextLine();
			parseLine(line);
//			System.out.print("> ");
		};
	}

	public void parse(String filename) {
		Path filePath = Paths.get(filename);
		try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				parseLine(line);
			};
		} catch (IOException e) {
			System.out.println("Open '" + filename + "' failed.");
		}
		parse();
	}

	private void parseLine(String line) {
		parseLine(line, globalStack);
	}

	public static void parseLine(String line, MuaStack stack) {
		// remove comments
		int commentIndex = line.indexOf("//");
		if (commentIndex != -1) {
			line = line.substring(0, commentIndex);
		}
		stack.scanner = new Scanner(line);
		while (stack.scanner.hasNext()) {
			String str = stack.scanner.next();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			if (finishStatement()) {
				stack.processStatement(items);
			}
		};
	}

	private static boolean finishStatement() {
		return !ValueFactory.isParsingList();
	}

}
