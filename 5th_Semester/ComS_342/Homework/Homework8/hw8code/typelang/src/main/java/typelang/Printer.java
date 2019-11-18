package typelang;

import java.util.List;

import typelang.Type;
import typelang.AST.Exp;

public class Printer {
	public void print(Value v) {
		if(v.tostring() != "")
			System.out.println(v.tostring());
	}
	public void print(Type t) {
		if(t.tostring() != "")
			System.out.println(t.tostring());
	}
	public void print(Exception e) {
		System.out.println(e.toString());
	}
	
	public static class Formatter implements AST.Visitor<String,Env<Void>> {
		
		public String visit(AST.AddExp e, Env<Void> env) {
			String result = "(+ ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.UnitExp e, Env<Void> env) {
			return "unit";
		}

		public String visit(AST.NumExp e, Env<Void> env) {
			return "" + e.v();
		}
		
		public String visit(AST.StrExp e, Env<Void> env) {
			return e.v();
		}
		
		public String visit(AST.BoolExp e, Env<Void> env) {
			if(e.v()) return "#t";
			return "#f";
		}

		public String visit(AST.DivExp e, Env<Void> env) {
			String result = "(/ ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.ReadExp e, Env<Void> env) {
			return "(read " + e.file().accept(this, env) + ")";
		}

		public String visit(AST.EvalExp e, Env<Void> env) {
			return "(eval " + e.code().accept(this, env) + ")";
		}

		public String visit(AST.MultExp e, Env<Void> env) {
			String result = "(* ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.Program p, Env<Void> env) {
			return "" + p.e().accept(this, env);
		}
		
		public String visit(AST.SubExp e, Env<Void> env) {
			String result = "(- ";
			for(AST.Exp exp : e.all()) 
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.VarExp e, Env<Void> env) {
			return "" + e.name();
		}
		
		public String visit(AST.LetExp e, Env<Void> env) {
			String result = "(let (";
			List<String> names = e.names();
			List<Exp> value_exps = e.value_exps();
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
		
		public String visit(AST.DefineDecl d, Env<Void> env) {
			String result = "(define ";
			result += d.name() + " ";
			result += d.value_exp().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.LambdaExp e, Env<Void> env) {
			String result = "(lambda ( ";
			for(String formal : e.formals()) 
				result += formal + " ";
			result += ") ";
			result += e.body().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.CallExp e, Env<Void> env) {
			String result = "(";
			result += e.operator().accept(this, env) + " ";
			for(AST.Exp exp : e.operands())
				result += exp.accept(this, env) + " ";
			return result + ")";
		}
		
		public String visit(AST.IfExp e, Env<Void> env) {
			String result = "(if ";
			result += e.conditional().accept(this, env) + " ";
			result += e.then_exp().accept(this, env) + " ";
			result += e.else_exp().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.LessExp e, Env<Void> env) {
			String result = "(< ";
			result += e.first_exp().accept(this, env) + " ";
			result += e.second_exp().accept(this, env);
			return result + ")";
		}

		public String visit(AST.EqualExp e, Env<Void> env) {
			String result = "(= ";
			result += e.first_exp().accept(this, env) + " ";
			result += e.second_exp().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.GreaterExp e, Env<Void> env) {
			String result = "(> ";
			result += e.first_exp().accept(this, env) + " ";
			result += e.second_exp().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.CarExp e, Env<Void> env) {
			String result = "(car ";
			result += e.arg().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.CdrExp e, Env<Void> env) {
			String result = "(cdr ";
			result += e.arg().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.ConsExp e, Env<Void> env) {
			String result = "(cons ";
			result += e.fst().accept(this, env) + " ";
			result += e.snd().accept(this, env);
			return result + ")";
		}
		
		public String visit(AST.ListExp e, Env<Void> env) {
			String result = "(list ";
			for(AST.Exp exp : e.elems())
				result += exp.accept(this, env) + " ";
			return result + ")";
		}

		public String visit(AST.NullExp e, Env<Void> env) {
			String result = "(null? ";
			result += e.arg().accept(this, env);
			return result + ")";
		}

		public String visit(AST.RefExp e, Env<Void> env) {
			String result = "(ref ";
			result += e.value_exp().accept(this, env);
			return result + ")";
		}

		public String visit(AST.DerefExp e, Env<Void> env) {
			String result = "(deref ";
			result += e.loc_exp().accept(this, env);
			return result + ")";
		}

		public String visit(AST.AssignExp e, Env<Void> env) {
			String result = "(set! ";
			result += e.lhs_exp().accept(this, env) + " ";
			result += e.rhs_exp().accept(this, env);
			return result + ")";
		}

		public String visit(AST.FreeExp e, Env<Void> env) {
			String result = "(free ";
			result += e.value_exp().accept(this, env);
			return result + ")";
		}
	}
}
