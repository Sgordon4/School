import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static java.lang.System.exit;

public class PFModified {

    // Is the input character an operator or a '('
    static boolean isOperator(String input){
        Set<String> operators = new HashSet<String>(Arrays.asList("+", "-", "*", "%", "/", "("));
        return operators.contains(input);
    }

    // function to return precedence value
    // if operator is present in stack
    static int stackPrecedence(String input)
    {
        switch (input) {
            case "+":
            case "-":
                return 2;
            case "*":
            case "%":
            case "/":
                return 4;
            case "(":
                return 0;
        }
        return 0;
    }

    // function to return precedence value
    // if operator is present outside stack.
    static int inputPrecedence(String input)
    {
        switch (input) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "%":
            case "/":
                return 3;
            case "(":
                return 100;
        }
        return 0;
    }


    // function to convert infix to postfix
    static void inToPost(String[] input)
    {
        Stack<String> s = new Stack<String>();


        for(String in : input){

            // if input is an operator, push
            if (isOperator(in)) {
                if (!(s.empty() || inputPrecedence(in) > stackPrecedence(s.peek()))) {
                    while (!s.empty() && inputPrecedence(in) < stackPrecedence(s.peek()))
                        System.out.print(s.pop());
                }
                s.push(in);
            }

            // condition for opening bracket
            else if (in.equals(")")) {
                while (!s.peek().equals("(")) {
                    System.out.print(s.pop());

                    // if opening bracket not present
                    if (s.empty()) {
                        System.out.println("Wrong input\n");
                        exit(1);
                    }
                }

                // pop the opening bracket.
                s.pop();
            }

            // if character an operand
            else
                System.out.print(in);
        }

        // pop the remaining operators
        while (!s.empty()) {
            if (s.peek().equals("(")) {
                System.out.println("\n Wrong input\n");
                exit(1);
            }
            System.out.print(s.pop());
        }
    }

    // Driver code
    static public void main(String[] args)
    {
        String[] input ="a+b*c-(d/e+f*g*h)".split("");
        String[] input2 ="A+B*C+D".split("");
        String[] input3 ="(A+B)*(C+D)".split("");
        String[] input4 ="A*B+C*D".split("");
        String[] input5 ="A+B+C+D".split("");
        inToPost(input);
        System.out.println();
        inToPost(input2);
        System.out.println();
        inToPost(input3);
        System.out.println();
        inToPost(input4);
        System.out.println();
        inToPost(input5);
        System.out.println();
    }
}
