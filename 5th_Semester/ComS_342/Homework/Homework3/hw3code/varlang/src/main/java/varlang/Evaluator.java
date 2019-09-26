package varlang;
import static varlang.AST.*;
import static varlang.Value.*;

import java.util.List;
import java.util.ArrayList;

import varlang.AST.AddExp;
import varlang.AST.NumExp;
import varlang.AST.DivExp;
import varlang.AST.MultExp;
import varlang.AST.Program;
import varlang.AST.SubExp;
import varlang.AST.VarExp;
import varlang.AST.Visitor;
import varlang.Env.EmptyEnv;
import varlang.Env.ExtendEnv;

public class Evaluator implements Visitor<Value> {

	//Make a global variables list here?
	List<ConstExp> globals = new ArrayList<ConstExp>();
	
	Value valueOf(Program p) {
		Env env = new EmptyEnv();

		//To implement define, add the global variables to the environment first
        for(ConstExp constExp : globals){
			env = new ExtendEnv(env, constExp.name(), new NumVal(constExp.v()));
		}

		// Value of a program in this language is the value of the expression
		return (Value) p.accept(this, env);
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
	public Value visit(NumExp e, Env env) {
		return new NumVal(e.v());
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
		return (Value) p.e().accept(this, env);
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
	public Value visit(DecExp e, Env env) {
		// Previously, all variables had value 42. New semantics.
		return decrypt(e.key(), (NumVal) env.get(e.name()));
	}

	public Value decrypt(double key, NumVal val){
		//Im just gunna assume its always a double

		return new NumVal(val.v() - key);
	}

	@Override
	public Value visit(ConstExp e, Env env) {
		this.globals.add(e);

		//System.out.println("Defined *dabs*");

		return new NumVal(e.v());
	}

	@Override
	public Value visit(LetExp e, Env env) { // New for varlang.
		/*
		List<String> names = e.names();
		List<Exp> value_exps = e.value_exps();
		List<Value> values = new ArrayList<Value>(value_exps.size());
		
		for(Exp exp : value_exps)
			values.add((Value)exp.accept(this, env));

		
		Env new_env = env;
		for (int i = 0; i < names.size(); i++)
			new_env = new ExtendEnv(new_env, names.get(i), values.get(i));
		 */

		// ------ Above turned into below: ------ \\
		// Lumped both operations into a single for loop
		// Passed the new environment with the updated variables every time


		List<String> names = e.names();
		List<Exp> value_exps = e.value_exps();
		List<Value> values = new ArrayList<Value>(value_exps.size());
		Env new_env = env;

		for (int i = 0; i < names.size(); i++) {
			Exp exp = value_exps.get(i);

			values.add((Value) exp.accept(this, new_env));

			new_env = new ExtendEnv(new_env, names.get(i), values.get(i));
		}


		return (Value) e.body().accept(this, new_env);		
	}



    @Override
    public Value visit(LeteExp e, Env env) { // New for varlang.
        List<String> names = e.names();
        List<Exp> value_exps = e.value_exps();
        List<Value> values = new ArrayList<Value>(value_exps.size());
        Env new_env = env;

        for (int i = 0; i < names.size(); i++) {
            Exp exp = value_exps.get(i);

            values.add((Value) exp.accept(this, new_env));

            new_env = new ExtendEnv(new_env, names.get(i), values.get(i));
        }


        return (Value) e.body().accept(this, new_env);
    }

}
