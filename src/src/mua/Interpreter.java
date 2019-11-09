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
	private NameSpace globalNameSpace = new NameSpace();
	private MuaStack globalStack = new MuaStack(globalNameSpace);

	public static Scanner ioScanner = new Scanner(System.in);

	private ArrayList<MuaItem> muaStatement = new ArrayList<>();

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

		Scanner scanner = new Scanner(line);
		while (scanner.hasNext()) {
			String str = scanner.next();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			this.muaStatement.addAll(items);
		};
		if (finishStatement()) {
			this.processStatement();
		}
	}

	private boolean finishStatement() {
		return !ValueFactory.isParsingList();
	}

	private void processStatement() {
		globalStack.processStatement(this.muaStatement);
		this.muaStatement.clear();
	}
}
