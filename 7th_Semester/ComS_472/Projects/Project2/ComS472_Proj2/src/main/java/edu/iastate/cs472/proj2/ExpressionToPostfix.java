package edu.iastate.cs472.proj2;

import java.util.*;

import static java.lang.System.exit;

/**
 * @author Sean Gordon
 */
public class ExpressionToPostfix {

    static Set<String> operators = new HashSet<String>(Arrays.asList("~", "&&", "||", "<=>", "=>", "("));

    /**
     * Given a list of tokens making up an infix propositional logic sentence, transforms the list into postfix.
     *
     * @param input List of tokens making up a propositional logic sentence
     * @return List<String> Postfix order of input string
     */
    static List<String> infixToPostfix(List<String> input) {
        Stack<String> s = new Stack<String>();
        List<String> postfix = new ArrayList<String>();


        for(String in : input){

            // if input is an operator, push
            if (operators.contains(in)) {
                if (!(s.empty() || inputPrecedence(in) > stackPrecedence(s.peek()))) {
                    while (!s.empty() && inputPrecedence(in) < stackPrecedence(s.peek()))
                        postfix.add(s.pop());
                }
                s.push(in);
            }

            // condition for opening bracket
            else if (in.equals(")")) {
                while (!s.peek().equals("(")) {
                    postfix.add(s.pop());

                    // if opening bracket not present
                    if (s.empty()) {
                        System.out.println("\nMissing opening bracket\n");
                        exit(1);
                    }
                }

                // pop the opening bracket.
                s.pop();
            }

            // if character an operand
            else
                postfix.add(in);
        }

        // pop the remaining operators
        while (!s.empty()) {
            if (s.peek().equals("(")) {
                System.out.println("\nMissing closing bracket\n");
                exit(1);
            }
            postfix.add(s.pop());
        }


        return postfix;
    }

    // function to return precedence value
    // if operator is present in stack
    static int stackPrecedence(String input) {
        switch (input) {
            case "~": return 5;
            case "&&": return 4;
            case "||": return 3;
            case "=>": return 2;
            case "<=>": return 1;
            case "(": return -1;
            default: return -100;
        }
    }

    // function to return precedence value
    // if operator is present outside stack.
    static int inputPrecedence(String input)  {
        switch (input) {
            case "~": return 5;
            case "&&": return 4;
            case "||": return 3;
            case "=>": return 2;
            case "<=>": return 1;
            case "(": return 6;
            default: return -100;
        }
    }
}
