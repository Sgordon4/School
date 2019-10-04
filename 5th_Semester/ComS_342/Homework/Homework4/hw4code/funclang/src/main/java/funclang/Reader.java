package funclang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileReader;

import funclang.AST.Program;
import funclang.parser.FuncLangLexer;
import funclang.parser.FuncLangParser;

public class Reader {
	
	Program read() throws IOException {
		String programText = readNextProgram();
		return parse(programText);
	}

	Program parse(String programText) {
		FuncLangLexer l = new FuncLangLexer(new org.antlr.v4.runtime.ANTLRInputStream(programText));
		FuncLangParser p = new FuncLangParser(new org.antlr.v4.runtime.CommonTokenStream(l));
		Program program = p.program().ast;
		return program;
	}
	
	static String readFile(String fileName) throws IOException {
		try (BufferedReader br = new BufferedReader(
				new FileReader(fileName))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		}
	}
	
	private String readNextProgram() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("$ ");
		String programText = br.readLine();
		return runFile(programText);
	}
	
	private String runFile(String programText) throws IOException {
		if(programText.startsWith("run ")){
			programText = readFile("build/funclang/examples/" + programText.substring(4));
		}
		return programText; 
	}
}
