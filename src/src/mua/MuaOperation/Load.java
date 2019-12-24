package src.mua.MuaOperation;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import src.mua.MuaStack;
import src.mua.MuaValue.MuaNone;
import src.mua.MuaValue.MuaValue;
import src.mua.MuaValue.MuaWord;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Load implements MuaOperation {
	@Override
	public int argNumber() {
		return 1;
	}

	@Override
	public MuaValue execute(ArrayList<MuaValue> args, MuaStack caller) {
		String path = MuaWord.convertFrom(args.get(0)).getWord();
		StringBuilder sb = new StringBuilder();
		try {
			FileReader f = new FileReader(path);
			BufferedReader br = new BufferedReader(f);
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s).append("\n");
			}
//			System.out.println(sb.toString());
			f.close();

		} catch (IOException e) {
			System.out.println("Open file '" + path + "' failed.");
		}

		MuaStack stack = new MuaStack(caller.getNameSpace());
		stack.parseLine(sb.toString());

		return new MuaNone();
	}
}
