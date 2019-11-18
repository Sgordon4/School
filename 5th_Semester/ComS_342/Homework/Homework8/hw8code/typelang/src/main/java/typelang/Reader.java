package typelang;

import org.antlr.v4.runtime.Lexer;

import typelang.AST.*;
import typelang.parser.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
	
	public Program read() throws IOException {
		String programText = readNextProgram(getProgramDirectory());
		return parse(programText);
	}
	
	public Program parse(String programText) {
		Lexer l = getLexer(new org.antlr.v4.runtime.ANTLRInputStream(programText));
		TypeLangParser p = getParser(new org.antlr.v4.runtime.CommonTokenStream(l));
		Program program = p.program().ast;
		return program;
	}
	
	protected Lexer getLexer(org.antlr.v4.runtime.ANTLRInputStream s) {
		return new TypeLangLexer(s);
	}
	
	protected TypeLangParser getParser(org.antlr.v4.runtime.CommonTokenStream s) {
		return new TypeLangParser(s);
	}
			
	protected String getProgramDirectory() { 
		String buildDir = "build" + java.io.File.separatorChar + "reflang" + java.io.File.separatorChar + "examples" + java.io.File.separatorChar;
		return buildDir; 
	}
	
	public static String readFile(String fileName) throws IOException {
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
	
	public static String runFile(String programText, String programDirectory) throws IOException {
		if(programText.startsWith("run ")){
			programText = readFile(programDirectory + programText.substring(4));
		}
		return programText; 
	}	
	
	public static String readNextProgram(String programPath) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("$ ");
		String programText = br.readLine();
		return runFile(programText, programPath);
	}

}
