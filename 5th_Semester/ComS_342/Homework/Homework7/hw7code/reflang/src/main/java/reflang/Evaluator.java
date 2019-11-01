package reflang;
import static reflang.AST.*;
import static reflang.Value.*;

import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

import reflang.Env.*;

public class Evaluator implements Visitor<Value> {

	Printer.Formatter ts = new Printer.Formatter();

	Env initEnv = initialEnv(); //New for definelang

	Value valueOf(Program p) {
			return (Value) p.accept(this, initEnv);
	}

	@Override
	public Value visit(AddExp e, Env env) {
		List<Exp> operands = e.all();
		double result = 0;
		for(Exp exp: operands) {
			NumVal intermediate = (NumVal) exp.accept(this, env); // Dynamic type-checking
			result += intermediate.v(); //Semantics of AddExp in terms of the target language.
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(UnitExp e, Env env) {
		return new UnitVal();
	}

	@Override
	public Value visit(NumExp e, Env env) {
		return new NumVal(e.v());
	}

	@Override
	public Value visit(StrExp e, Env env) {
		return new StringVal(e.v());
	}

	@Override
	public Value visit(BoolExp e, Env env) {
		return new BoolVal(e.v());
	}

	@Override
	public Value visit(DivExp e, Env env) {
		List<Exp> operands = e.all();
		NumVal lVal = (NumVal) operands.get(0).accept(this, env);
		double result = lVal.v();
		for(int i=1; i<operands.size(); i++) {
			NumVal rVal = (NumVal) operands.get(i).accept(this, env);
			result = result / rVal.v();
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(MultExp e, Env env) {
		List<Exp> operands = e.all();
		double result = 1;
		for(Exp exp: operands) {
			NumVal intermediate = (NumVal) exp.accept(this, env); // Dynamic type-checking
			result *= intermediate.v(); //Semantics of MultExp.
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(Program p, Env env) {
		try {
			for(DefineDecl d: p.decls())
				d.accept(this, initEnv);
			return (Value) p.e().accept(this, initEnv);
		} catch (ClassCastException e) {
			return new DynamicError(e.getMessage());
		}
	}

	@Override
	public Value visit(SubExp e, Env env) {
		List<Exp> operands = e.all();
		NumVal lVal = (NumVal) operands.get(0).accept(this, env);
		double result = lVal.v();
		for(int i=1; i<operands.size(); i++) {
			NumVal rVal = (NumVal) operands.get(i).accept(this, env);
			result = result - rVal.v();
		}
		return new NumVal(result);
	}

	@Override
	public Value visit(VarExp e, Env env) {
		// Previously, all variables had value 42. New semantics.
		return env.get(e.name());
	}

	@Override
	public Value visit(LetExp e, Env env) { // New for varlang.
		List<String> names = e.names();
		List<Exp> value_exps = e.value_exps();
		List<Value> values = new ArrayList<Value>(value_exps.size());

		for(Exp exp : value_exps)
			values.add((Value)exp.accept(this, env));

		Env new_env = env;
		for (int index = 0; index < names.size(); index++)
			new_env = new ExtendEnv(new_env, names.get(index), values.get(index));

		return (Value) e.body().accept(this, new_env);
	}

	@Override
	public Value visit(DefineDecl e, Env env) { // New for definelang.
		String name = e.name();
		Exp value_exp = e.value_exp();
		Value value = (Value) value_exp.accept(this, env);
		((GlobalEnv) initEnv).extend(name, value);
		return new Value.UnitVal();
	}

	@Override
	public Value visit(LambdaExp e, Env env) { // New for reflang.
		return new Value.FunVal(env, e.formals(), e.body());
	}

	@Override
	public Value visit(CallExp e, Env env) { // New for reflang.
		Object result = e.operator().accept(this, env);
		if(!(result instanceof Value.FunVal))
			return new Value.DynamicError("Operator not a function in call " +  ts.visit(e, env));
		Value.FunVal operator =  (Value.FunVal) result; //Dynamic checking
		List<Exp> operands = e.operands();

		// Call-by-value semantics
		List<Value> actuals = new ArrayList<Value>(operands.size());
		for(Exp exp : operands)
			actuals.add((Value)exp.accept(this, env));

		List<String> formals = operator.formals();
		if (formals.size()!=actuals.size())
			return new Value.DynamicError("Argument mismatch in call " + ts.visit(e, env));

		Env fun_env = operator.env();
		for (int index = 0; index < formals.size(); index++)
			fun_env = new ExtendEnv(fun_env, formals.get(index), actuals.get(index));

		return (Value) operator.body().accept(this, fun_env);
	}

	@Override
	public Value visit(IfExp e, Env env) { // New for reflang.
		Object result = e.conditional().accept(this, env);
		if(!(result instanceof Value.BoolVal))
			return new Value.DynamicError("Condition not a boolean in expression " +  ts.visit(e, env));
		Value.BoolVal condition =  (Value.BoolVal) result; //Dynamic checking

		if(condition.v())
			return (Value) e.then_exp().accept(this, env);
		else return (Value) e.else_exp().accept(this, env);
	}

  @Override
	public Value visit(LetrecExp e, Env env) { // New for reclang.
		List<String> names = e.names();
		List<Exp> fun_exps = e.fun_exps();
		List<Value.FunVal> funs = new ArrayList<Value.FunVal>(fun_exps.size());
		
		for(Exp exp : fun_exps) 
			funs.add((Value.FunVal)exp.accept(this, env));

		Env new_env = new ExtendEnvRec(env, names, funs);
		return (Value) e.body().accept(this, new_env);		
	}	

	public static boolean equalValue(Value v1, Value v2) {
		if (v1 instanceof NumVal && v2 instanceof NumVal) {
			NumVal first = (NumVal) v1;
			NumVal second = (NumVal) v2;
			return Double.compare(first.v(), second.v()) == 0;
		} else if (v1 instanceof StringVal && v2 instanceof StringVal) {
			String s1 = ((StringVal)v1).v();
			String s2 = ((StringVal)v2).v();
			return s1.equals(s2);
		} else if (v1 instanceof PairVal && v2 instanceof PairVal) {
			boolean b1 = equalValue(((PairVal)v1).fst(), ((PairVal)v2).fst());
			boolean b2 = equalValue(((PairVal)v1).snd(), ((PairVal)v2).snd());
			if (b1 && b2) return true;
			return false;
		} /*else if (v1 instanceof FunVal && v2 instanceof FunVal) {
			return v1 == v2;
		} */else if (v1 instanceof BoolVal && v2 instanceof BoolVal) {
			return ((BoolVal)v1).v() == ((BoolVal)v2).v();
		} else if (v1 instanceof Null && v2 instanceof Null){
			// list
			return true;
		} else {
			return false;
		}
	}

	public static int compareValue(Value v1, Value v2) {
		if (equalValue(v1, v2)) {
			return 0;
		}

		if (v1 instanceof NumVal && v2 instanceof NumVal) {
			NumVal first = (NumVal) v1;
			NumVal second = (NumVal) v2;
			return Double.compare(first.v(), second.v());
		} else if (v1 instanceof StringVal && v2 instanceof StringVal) {
			String s1 = ((StringVal)v1).v();
			String s2 = ((StringVal)v2).v();
			return s1.compareTo(s2);
		} else if (v1 instanceof PairVal && v2 instanceof PairVal) {
			PairVal p1 = (PairVal) v1;
			PairVal p2 = (PairVal) v2;
			if (p1.isList() && p2.isList()) {
				// we define a size method in PairVal
				return Integer.compare(p1.size(), p2.size());
			} else if (!p1.isList() && !p2.isList()) {
				// this case can be omitted in hw
				// if they are both pairs, the result cannot be applied
				return 0;
			}
		} else if (v1 instanceof PairVal && v2 instanceof Null) {
			return 1;
		} else if (v1 instanceof Null && v2 instanceof PairVal) {
			return -1;
		}

		// default case can be 1 or -1 to define different
		return -1;
	}

	@Override
	public Value visit(LessExp e, Env env) { // New for reflang.
		Value first = (Value) e.first_exp().accept(this, env);
		Value second = (Value) e.second_exp().accept(this, env);
		return new Value.BoolVal(compareValue(first, second) < 0);
	}

	@Override
	public Value visit(EqualExp e, Env env) { // New for reflang.
		Value v1 = (Value) e.first_exp().accept(this, env);
		Value v2 = (Value) e.second_exp().accept(this, env);
		return new BoolVal(equalValue(v1, v2));
	}

	@Override
	public Value visit(GreaterExp e, Env env) { // New for reflang.
		Value first = (Value) e.first_exp().accept(this, env);
		Value second = (Value) e.second_exp().accept(this, env);
		return new Value.BoolVal(compareValue(first, second) > 0);
	}

	@Override
	public Value visit(CarExp e, Env env) {
		Value.PairVal pair = (Value.PairVal) e.arg().accept(this, env);
		return pair.fst();
	}

	@Override
	public Value visit(CdrExp e, Env env) {
		Value.PairVal pair = (Value.PairVal) e.arg().accept(this, env);
		return pair.snd();
	}

	@Override
	public Value visit(ConsExp e, Env env) {
		Value first = (Value) e.fst().accept(this, env);
		Value second = (Value) e.snd().accept(this, env);
		return new Value.PairVal(first, second);
	}

	@Override
	public Value visit(ListExp e, Env env) { // New for reflang.
		List<Exp> elemExps = e.elems();
		int length = elemExps.size();
		if(length == 0)
			return new Value.Null();

		//Order of evaluation: left to right e.g. (list (+ 3 4) (+ 5 4))
		Value[] elems = new Value[length];
		for(int i=0; i<length; i++)
			elems[i] = (Value) elemExps.get(i).accept(this, env);

		Value result = new Value.Null();
		for(int i=length-1; i>=0; i--)
			result = new PairVal(elems[i], result);
		return result;
	}

	@Override
	public Value visit(NullExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.Null);
	}

	@Override
	public Value visit(LengthStrExp e, Env env) {
		Value val = (Value) e.getStrExpr().accept(this, env);
		if (val instanceof StringVal) {
			String string = ((StringVal) val).v();
			return new NumVal(string.length() - 2); // - 2 to remove double quotes
		}

		return new DynamicError("Parameter for length was not a string.");
	}

	@Override
	public Value visit(IsNumExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.NumVal);
	}

	@Override
	public Value visit(IsBoolExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.BoolVal);
	}

	@Override
	public Value visit(IsStringExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.StringVal);
	}

	//TODO
	@Override
	public Value visit(IsProcedureExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.FunVal);
	}

	@Override
	public Value visit(IsListExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		if(val instanceof Value.PairVal){
			return new BoolVal(((PairVal) val).isList());
		}
		else if(val instanceof Value.Null){
			return new BoolVal(true);
		}

		return new BoolVal(false);
	}

	@Override
	public Value visit(IsPairExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.PairVal);
	}

	@Override
	public Value visit(IsUnitExp e, Env env) {
		Value val = (Value) e.arg().accept(this, env);
		return new BoolVal(val instanceof Value.UnitVal);
	}

	public Value visit(EvalExp e, Env env) {
		StringVal programText = (StringVal) e.code().accept(this, env);
		Program p = _reader.parse(programText.v());
		return (Value) p.accept(this, env);
	}

	public Value visit(ReadExp e, Env env) {
		StringVal fileName = (StringVal) e.file().accept(this, env);
		try {
			String text = Reader.readFile("" + System.getProperty("user.dir") + File.separator + fileName.v());
			return new StringVal(text);
		} catch (IOException ex) {
			return new DynamicError(ex.getMessage());
		}
	}

	private Env initialEnv() {
		GlobalEnv initEnv = new GlobalEnv();

		/* Procedure: (read <filename>). Following is same as (define read (lambda (file) (read file))) */
		List<String> formals = new ArrayList<>();
		formals.add("file");
		Exp body = new AST.ReadExp(new VarExp("file"));
		Value.FunVal readFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("read", readFun);

		/* Procedure: (require <filename>). Following is same as (define require (lambda (file) (eval (read file)))) */
		formals = new ArrayList<>();
		formals.add("file");
		body = new EvalExp(new AST.ReadExp(new VarExp("file")));
		Value.FunVal requireFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("require", requireFun);

		/* Add new built-in procedures here */
		formals = new ArrayList<>();
		formals.add("str");
		body = new AST.LengthStrExp(new VarExp("str"));
		Value.FunVal lengthFun = new Value.FunVal(initEnv, formals, body);
		initEnv.extend("length", lengthFun);

		return initEnv;
	}

	Reader _reader;
	public Evaluator(Reader reader) {
		_reader = reader;
	}
}
