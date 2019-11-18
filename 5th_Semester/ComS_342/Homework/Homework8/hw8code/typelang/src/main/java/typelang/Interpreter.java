package typelang;
import java.io.IOException;

import typelang.Checker;
import typelang.Env;
import typelang.Type;
import typelang.Value;
import typelang.AST.*;

/**
 * This main class implements the Read-Eval-Print-Loop of the interpreter with
 * the help of Reader, Evaluator, and Printer classes. 
 * 
 * @author hridesh
 *
 */
public class Interpreter {
	public static void main(String[] args) {
		System.out.println("TypeLang: Type a program to evaluate and press the enter key,\n"  
				+ "e.g. ((lambda (x : num y : num z : num) (+ x (+ y z))) 1 2 3) \n" + 
				"or try (let ((x : num 2)) x) \n" +
				"or try (car (list : num  1 2 8)) \n" +
				"or try (ref : num 2) \n" + 
				"or try  (let ((a : Ref num (ref : num 2))) (set! a (deref a))) \n" +
				"Press Ctrl + C to exit.");
		Reader reader = new Reader();
		Evaluator eval = new Evaluator(reader);
		Printer printer = new Printer();
		Checker checker = new Checker(); // Type checker
		REPL: while (true) { // Read-Eval-Print-Loop (also known as REPL)
			Program p = null;
			try {
				p = reader.read();
				if(p._e == null) continue REPL;
				Type t = checker.check(p); /*** Type checking the program ***/
				if(t instanceof Type.ErrorT)
					printer.print(t);
				else {
					try {
						Value val = eval.valueOf(p);
						printer.print(val);
					} catch (Env.LookupException e) {
						printer.print(e);
					}
				}
			} catch (Env.LookupException e) {
				printer.print(e);
			} catch (IOException e) {
				System.out.println("Error reading input:" + e.getMessage());
			} catch (NullPointerException e) {
				System.out.println("Error:" + e.getMessage());
			}
		}
	}
}
