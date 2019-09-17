package arithlang;
import static arithlang.AST.*;
import static arithlang.Value.*;

import java.util.List;

public class Evaluator implements Visitor<Value> {
    private CharVal record = new CharVal('0');
    Printer.Formatter ts = new Printer.Formatter();
	
    Value valueOf(Program p) {
        // Value of a program in this language is the value of the expression
        return (Value) p.accept(this);
    }
	


    @Override
    public Value visit(CharExp e) {
        return new CharVal(e.v());
    }

    @Override
    public Value visit(AddExp e) {
        System.out.println("Adding");

        List<Exp> operands = e.all();
        int length = operands.size();

        if(length != 2) {
            System.out.println("Only two operators allowed");
            return new DynamicError(ts.visit(e));
        }

        /*
        char result = 0;
        for(Exp exp: operands) {
            CharVal intermediate = (CharVal) exp.accept(this); // Dynamic type-checking
            result += intermediate.v(); //Semantics of AddExp in terms of the target language.
        }
        return new CharVal(result);

         */
        return new CharVal('a');
    }

    @Override
    public Value visit(MultExp e) {
        System.out.println("Multiplying");

        List<Exp> operands = e.all();
        int length = operands.size();

        if(length != 2) {
            System.out.println("Only two operators allowed");
            return new DynamicError(ts.visit(e));
        }


        CharVal val1 = (CharVal) operands.get(0).accept(this);
        char char1 = val1.v();
        CharVal val2 = (CharVal) operands.get(1).accept(this);
        char char2 = val2.v();



        char result;
        //Switch case that implements the rules outlined in the HW2 PDF
        switch(char1){
            case 'e': 
        }

        System.out.println(char1);
        System.out.println(char2);

        /*
        char result = 0;
        for(Exp exp: operands) {
            CharVal intermediate = (CharVal) exp.accept(this); // Dynamic type-checking
            result *= intermediate.v(); //Semantics of MultExp.
        }
        return new CharVal(result);
         */
        return new CharVal('m');
    }

    @Override
    public Value visit(Program p) {
        return (Value) p.e().accept(this);
    }

}
