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

	private ArrayList<MuaItem> muaStatement = new ArrayList<>();

	public Interpreter() {}

	public void parse() {
//		System.out.print("> ");
		while (ioScanner.hasNextLine()) {
			String str = ioScanner.nextLine();
			this.muaStatement.addAll(parseLine(str));
			if (finishStatement()) {
				this.processStatement();
			}
//			System.out.print("> ");
		};
	}

	public void parse(String filename) {
		Path filePath = Paths.get(filename);
		try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				this.muaStatement.addAll(parseLine(str));
				if (finishStatement()) {
					this.processStatement();
				}
			};
		} catch (IOException e) {
			System.out.println("Open '" + filename + "' failed.");
		}
		parse();
	}

	public static ArrayList<MuaItem> parseLine(String line) {
		// remove comments
		int commentIndex = line.indexOf("//");
		if (commentIndex != -1) {
			line = line.substring(0, commentIndex);
		}

		Scanner scanner = new Scanner(line);
		ArrayList<MuaItem> muaStatement = new ArrayList<>();
		while (scanner.hasNext()) {
			String str = scanner.next();
			ArrayList<MuaItem> items = MuaItemFactory.parseLiteral(str);
			muaStatement.addAll(items);
		};
		return muaStatement;
	}

	private boolean finishStatement() {
		return !ValueFactory.isParsingList();
	}

	private void processStatement() {
		globalStack.processStatement(this.muaStatement);
		this.muaStatement.clear();
	}
}
