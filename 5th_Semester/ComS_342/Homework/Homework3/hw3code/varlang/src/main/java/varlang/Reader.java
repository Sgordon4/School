package varlang;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import varlang.parser.*;
import varlang.AST.*;

public class Reader {	
	Program read() throws IOException {
		String programText = readNextProgram();
		VarLangLexer l = new VarLangLexer(new org.antlr.v4.runtime.ANTLRInputStream(programText));
		VarLangParser p = new VarLangParser(new org.antlr.v4.runtime.CommonTokenStream(l));
		Program program = p.program().ast;
		return program;
	}
		
	private String readNextProgram() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("$ ");
		String programText = br.readLine();
		return programText;
	}
}
