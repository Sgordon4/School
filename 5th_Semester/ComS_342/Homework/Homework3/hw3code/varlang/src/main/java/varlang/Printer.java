package varlang;

import java.util.List;

public class Printer {
	public void print(Value v) {
		System.out.println(v.toString());
	}
	public void print(Exception e) {
		System.out.println(e.getMessage());
	}
	
	public static class Formatter implements AST.Visitor<String> {
		
		public String visit(AST.AddExp e, Env env) {
			String result = "(+ ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.NumExp e, Env env) {
			return "" + e.v();
		}
		
		public String visit(AST.DivExp e, Env env) {
			String result = "(/ ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.MultExp e, Env env) {
			String result = "(* ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.Program p, Env env) {
			return "" + p.e().accept(this, env);
		}
		
		public String visit(AST.SubExp e, Env env) {
			String result = "(- ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.VarExp e, Env env) {
			return "" + e.name();
		}
		
		public String visit(AST.LetExp e, Env env) {
			String result = "(let (";
			List<String> names = e.names();
			List<AST.Exp> value_exps = e.value_exps();
			int num_decls = names.size();
			for (int i = 0; i < num_decls ; i++) {
				result += " (";
				result += names.get(i) + " ";
				result += value_exps.get(i).accept(this, env) + ")";
			}
			result += ") ";
			result += e.body().accept(this, env) + " ";
			return result + ")";
		}
		
	}
}
