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
        //System.out.println("Adding");

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
        //Using ascii value combinations to determine output...
        //Ascii values for chars:
        //e - 101
        //o - 111
        //u - 117

        int ascii = (int)char1 + (int)char2;

        //Switch case that implements the rules outlined in the HW2 PDF
        switch(ascii){
            case 202: result = 'e'; //(* e e)
                break;
            case 212: result = 'o'; //(* e o) or (* o e)
                break;
            case 222: result = 'e'; //(* o o)
                break;
            default:  result = 'u'; //One operator is 'u'
        }

        return new CharVal(result);
    }

    @Override
    public Value visit(MultExp e) {
        //System.out.println("Multiplying");

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
        //Using ascii value combinations to determine output...
        //Ascii values for chars:
        //e - 101
        //o - 111
        //u - 117

        int ascii = (int)char1 + (int)char2;

        //Switch case that implements the rules outlined in the HW2 PDF
        switch(ascii){
            case 202: result = 'e'; //(* e e)
                break;
            case 212: result = 'e'; //(* e o) or (* o e)
                break;
            case 222: result = 'o'; //(* o o)
                break;
            default:  result = 'u'; //One operator is 'u'
        }

        return new CharVal(result);
    }

    @Override
    public Value visit(Program p) {
        return (Value) p.e().accept(this);
    }

}
