package typelang;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import typelang.AST.*;
import typelang.Env.ExtendEnv;
import typelang.Env.GlobalEnv;
import typelang.Type.*;

public class Checker implements Visitor<Type,Env<Type>> {
	Printer.Formatter ts = new Printer.Formatter();
	Env<Type> initEnv = initialEnv(); //New for definelang
	
	private Env<Type> initialEnv() {
		GlobalEnv<Type> initEnv = new GlobalEnv<Type>();
		
		/* Type for procedure: (read <filename>). Following is same as (define read (lambda (file) (read file))) */
		List<Type> formalTypes = new ArrayList<Type>();
		formalTypes.add(new Type.StringT());
		initEnv.extend("read", new Type.FuncT(formalTypes, new Type.StringT()));

		/* Type for procedure: (require <filename>). Following is same as (define require (lambda (file) (eval (read file)))) */
		formalTypes = new ArrayList<Type>();
		formalTypes.add(new Type.StringT());
		initEnv.extend("eval", new Type.FuncT(formalTypes, new Type.UnitT()));
		
		/* Add type for new built-in procedures here */ 
		
		return initEnv;
	}
	
    Type check(Program p) {
		return (Type) p.accept(this, null);
	}

	public Type visit(Program p, Env<Type> env) {
		Env<Type> new_env = initEnv;

		for (DefineDecl d: p.decls()) {
			Type type = (Type)d.accept(this, new_env);

			if (type instanceof ErrorT) { return type; }

			Type dType = d.type();

			if (!type.typeEqual(dType)) {
				return new ErrorT("Expected " + dType + " found " + type + " in " + ts.visit(d, null));
			}

			new_env = new ExtendEnv<Type>(new_env, d.name(), dType);
		}
		return (Type) p.e().accept(this, new_env);
	}

	public Type visit(VarExp e, Env<Type> env) {
		try {
			return env.get(e.name());
		} catch(Exception ex) {
			return new ErrorT("Variable " + e.name() +
					" has not been declared in " + ts.visit(e, null));
		}
	}

	public Type visit(LetExp e, Env<Type> env) {
		// answer question 6
        List<Exp> exps = e.value_exps();
        List<Type> types = e.varTypes();

        //Assuming exps and types are the same length
        //Verify each declared type equals the actual type in 'types'
        for(int i = 0; i < exps.size(); i++){
            Type supposed = types.get(i);

            Exp exp = exps.get(i);
            Type actual = (Type) exp.accept(this, env);

            if(actual instanceof ErrorT) {return actual;}
            //If the type isn't as it should be...
            if(!(actual.typeEqual(supposed))){
                return new ErrorT("The declared type of the "+i+" let variable and the "+
                        "actual type mismatch, expect "+supposed.tostring()+" type, "+
                        "found "+actual.tostring()+" in "+ts.visit(e, null));
            }
        }

        //Add onto our current environment for each variable
        List<String> names = e.names();
        Env<Type> updated = env;
        for(int i = 0; i < names.size(); i++){
            updated = new ExtendEnv<>(updated, names.get(i), types.get(i));
        }

        //Return all info with the updated environment
        return (Type) e.body().accept(this, updated);
	}

	public Type visit(DefineDecl d, Env<Type> env) {
		String name = d.name();
		Type t =(Type) d._value_exp.accept(this, env);
		((GlobalEnv<Type>) initEnv).extend(name, t);
		return t;
	}

	public Type visit(LambdaExp e, Env<Type> env) {
		List<String> names = e.formals();
		List<Type> types = e.types();
		String message = "The number of formal parameters and the number of "
				+ "arguments in the function type do not match in ";
		if (types.size() == names.size()) {
			Env<Type> new_env = env;
			int index = 0;
			for (Type argType : types) {
				new_env = new ExtendEnv<Type>(new_env, names.get(index),
						argType);
				index++;
			}

			Type bodyType = (Type) e.body().accept(this, new_env);
			return new FuncT(types,bodyType);
		}
		return new ErrorT(message + ts.visit(e, null));
	}

	public Type visit(CallExp e, Env<Type> env) {
        // answer question 7
        Exp operator = e.operator();
        Type opType = (Type) operator.accept(this, env);

        if (opType instanceof ErrorT) {return opType;}
        if (!(opType instanceof FuncT)) {
            return new ErrorT("Expect a function type in the call expression, found " +
                    opType.tostring() + " in " + ts.visit(e, null));
        }

        
        //Grab the supposed types of everything in the function
        FuncT func = (FuncT) opType;
        List<Type> funcTypes = func.argTypes();

        //Grab the operands
        List<Exp> operands = e.operands();

        //Assuming funcTypes and operands are the same length
        //Verify each declared type equals the actual type in 'funcTypes'
        for (int i = 0; i < operands.size(); i++) {
            Type supposed = funcTypes.get(i);

            Exp val = operands.get(i);
            Type actual = (Type) val.accept(this, env);

            if (actual instanceof ErrorT) {
                return actual;
            }
            //If we can't add the value to the list because it's the incorrect type...
            if (!assignable(supposed, actual)) {
                return new ErrorT("The expected type of actual parameter " + i +
                        " is " + supposed.tostring() + ", found " + actual.tostring() +
                        " in " + ts.visit(e, null));
            }
        }

        return func.returnType();
    }

	public Type visit(IfExp e, Env<Type> env) {
		// answer question 5
        Exp cond = e.conditional();
        Type type = (Type) cond.accept(this, env);

        if(type instanceof ErrorT) {return type;}
        if(!(type instanceof BoolT)){
            return new ErrorT("The condition should have boolean type, found "+
                                type.tostring()+" in "+ts.visit(e, null));
        }

        //Get the if and else types
        Type ifType = (Type) e.then_exp().accept(this, env);
        Type elseType = (Type) e.else_exp().accept(this, env);

        if(ifType instanceof ErrorT) {return ifType;}
        if(elseType instanceof ErrorT) {return elseType;}

        if(ifType.typeEqual(elseType)) {return ifType;}

		return new ErrorT("The then and else expressions should have the same type, "+
                                "then has type "+ifType.tostring()+" else has type "+
                                elseType.tostring()+" in "+ts.visit(e, null));
	}

	public Type visit(CarExp e, Env<Type> env) {
		Exp exp = e.arg();
		Type type = (Type)exp.accept(this, env);
		if (type instanceof ErrorT) { return type; }

		if (type instanceof PairT) {
			PairT pt = (PairT)type;
			return pt.fst();
		}

		return new ErrorT("The car expect an expression of type Pair, found "
				+ type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(CdrExp e, Env<Type> env) {
		// answer question 2(a)
        Exp e1 = e.arg();
        Type T = (Type) e1.accept(this, env);

        if(T instanceof ErrorT) { return T; }
        //If not an error, return the second value
        if(T instanceof PairT){ return ((PairT) T).snd(); }

		return new ErrorT("The cdr expect an expression of type Pair, found "+
                            T.tostring()+" in "+ts.visit(e, null));
	}

	public Type visit(ConsExp e, Env<Type> env) {
		Exp fst = e.fst(); 
		Exp snd = e.snd();

		Type t1 = (Type)fst.accept(this, env);
		if (t1 instanceof ErrorT) { return t1; }

		Type t2 = (Type)snd.accept(this, env);
		if (t2 instanceof ErrorT) { return t2; }

		return new PairT(t1, t2);
	}

	public Type visit(ListExp e, Env<Type> env) {
		// answer question 2(b)
        List<Exp> elems = e.elems();
        Type T = e.type();

        for(int i = 0; i < elems.size(); i++){
            Exp elem = elems.get(i);
            Type type = (Type) elem.accept(this, env);

            if(type instanceof ErrorT) { return type; }
            //If this type cannot be added to the list...
            if(!assignable(T, type)){
                return new ErrorT("The "+i+" expression should have type "+T.tostring()+", found "+
                                    type.tostring()+" in "+ts.visit(e, null));
            }
        }

		return new ListT(T);
	}

	public Type visit(NullExp e, Env<Type> env) {
		Exp arg = e.arg();
		Type type = (Type)arg.accept(this, env);
		if (type instanceof ErrorT) { return type; }

		if (type instanceof ListT) { return BoolT.getInstance(); }

		return new ErrorT("The null? expect an expression of type List, found "
				+ type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(RefExp e, Env<Type> env) {
		// answer question 1(a)
        Exp exp = e.value_exp();
        Type T = e.type();

        Type type = (Type)exp.accept(this, env);

        if(type instanceof ErrorT) { return type; }
        //if(type instanceof T) { return new RefT(type); }
        if(type.typeEqual(T)) {
            return new RefT(type);
        }

		return new ErrorT("The Ref expression expect type "+T.tostring()+", found "+
                                    type.tostring()+" in "+ts.visit(e, null));
	}

	public Type visit(DerefExp e, Env<Type> env) {
		Exp exp = e.loc_exp();
		Type type = (Type)exp.accept(this, env);
		if (type instanceof ErrorT) { return type; }

		if (type instanceof RefT) {
			RefT rt = (RefT)type;
			return rt.nestType();
		}

		return new ErrorT("The dereference expression expect a reference type " +
				" found " + type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(AssignExp e, Env<Type> env) {
		// answer question 1(b)
        Exp e1 = e.lhs_exp();
        Type T = (Type)e1.accept(this, env);

        if(T instanceof ErrorT) {return T;}
        if(T instanceof RefT){
            Exp e2 = e.rhs_exp();
            Type type = (Type)e2.accept(this, env);

            if(type instanceof ErrorT) {return type;}

            RefT refT = (RefT) T;
            Type TNestType = ((RefT) T).nestType();
            if(type.typeEqual(TNestType)) {return type;}

            //If we've gotten this far, error out
            return new ErrorT("The inner type of the reference type is "+TNestType.tostring()+
                    " the rhs type is "+type.tostring()+" in "+ts.visit(e, null));
        }
        return new ErrorT("The lhs of the assignment expression expect a reference type found "+
                T.tostring()+" in "+ts.visit(e, null));
	}

	public Type visit(FreeExp e, Env<Type> env) {
		Exp exp = e.value_exp();
		Type type = (Type)exp.accept(this, env);

		if (type instanceof ErrorT) { return type; }

		if (type instanceof RefT) { return UnitT.getInstance(); }

		return new ErrorT("The free expression expect a reference type " +
				"found " + type.tostring() + " in " + ts.visit(e, null));
	}

	public Type visit(UnitExp e, Env<Type> env) {
		return Type.UnitT.getInstance();
	}

	public Type visit(NumExp e, Env<Type> env) {
		return NumT.getInstance();
	}

	public Type visit(StrExp e, Env<Type> env) {
		return Type.StringT.getInstance();
	}

	public Type visit(BoolExp e, Env<Type> env) {
		return Type.BoolT.getInstance();
	}

	public Type visit(LessExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

	public Type visit(EqualExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

	public Type visit(GreaterExp e, Env<Type> env) {
		return visitBinaryComparator(e, env, ts.visit(e, null));
	}

	private Type visitBinaryComparator(BinaryComparator e, Env<Type> env,
			String printNode) {
		// answer question 4
        Exp e1 = e.first_exp();
        Exp e2 = e.second_exp();

        Type t1 = (Type) e1.accept(this, env);
        Type t2 = (Type) e2.accept(this, env);

        if(t1 instanceof ErrorT) { return t1; }
        if(t2 instanceof ErrorT) { return t2; }

        //If these types aren't NumT...
        if(!(t1 instanceof NumT)){
            return new ErrorT("The first argument of a binary expression should be num Type,"+
                    " found "+t1.tostring()+" in "+printNode);
        }
        if(!(t2 instanceof NumT)){
            return new ErrorT("The second argument of a binary expression should be num Type,"+
                    " found "+t2.tostring()+" in "+printNode);
        }

		return BoolT.getInstance();
	}


	public Type visit(AddExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(DivExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(MultExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	public Type visit(SubExp e, Env<Type> env) {
		return visitCompoundArithExp(e, env, ts.visit(e, null));
	}

	private Type visitCompoundArithExp(CompoundArithExp e, Env<Type> env, String printNode) {
		// answer question 3
        List<Exp> exps = e.all();

        for(Exp exp : exps){
            Type type = (Type) exp.accept(this, env);

            if(type instanceof ErrorT) { return type;}
            //If this isn't a number type...
            if(!(type instanceof Type.NumT)) {
                return new ErrorT("expected num found "+type.tostring()+" in "+printNode);
            }
        }

		return NumT.getInstance();
	}

	private static boolean assignable(Type t1, Type t2) {
		if (t2 instanceof UnitT) { return true; }

		return t1.typeEqual(t2);
	}
	
	public Type visit(ReadExp e, Env<Type> env) {
		return UnitT.getInstance();
	}

	public Type visit(EvalExp e, Env<Type> env) {
		return UnitT.getInstance();
	}
}
