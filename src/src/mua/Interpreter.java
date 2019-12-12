package src.mua;

import src.mua.Exception.MuaException;
import src.mua.MuaValue.MuaValueFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

	private final static Charset ENCODING = StandardCharsets.UTF_8;
	public static NameSpace globalNameSpace = new NameSpace();
	public static MuaStack globalStack = new MuaStack(globalNameSpace);

	public static Scanner ioScanner = new Scanner(System.in);

	public Interpreter() {
		preParse();
	}

	public void parse() {
//		System.out.value("> ");
		while (ioScanner.hasNextLine()) {
			String line = ioScanner.nextLine();
			try {
				globalStack.parseLine(removeComments(line));
			} catch (MuaException e) {
				e.print();
			}
//			System.out.value("> ");
		};
	}

	public void parse(String filename) {
		Path filePath = Paths.get(filename);
		try (Scanner scanner =  new Scanner(filePath, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				try {
					globalStack.parseLine(removeComments(line));
				} catch (MuaException e) {
					e.print();
				}
			};
		} catch (IOException e) {
			System.out.println("Open '" + filename + "' failed.");
		}
		parse();
	}

	private String removeComments(String line) {
		int commentIndex = line.indexOf("//");
		if (commentIndex != -1) {
			line = line.substring(0, commentIndex);
		}
		return line;
	}

	private void preParse() {
		String line =
				"make \"pi 3.14159\n" +
				"make \"run [\n" +
				"\t[list]\n" +
				"\t[repeat 1 :list]\n" +
				"]\n";
		globalStack.parseLine(line);
	}
}
