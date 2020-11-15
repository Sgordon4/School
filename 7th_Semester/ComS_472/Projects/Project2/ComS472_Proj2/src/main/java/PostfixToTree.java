import datatypes.Node;
import java.util.*;

public class PostfixToTree {
    static Set<String> operators = new HashSet<String>(Arrays.asList("~", "&&", "||", "<=>", "=>", "("));


    /**
     * Given a postfix list of tokens that make up a propositional logic sentence, build an expression tree.
     * Inspiration pulled from https://stackoverflow.com/questions/423898/postfix-notation-to-expression-tree
     *
     * @param expressions List of tokens that make up a propositional logic sentence in postfix order
     * @return Node Expression tree of the given propositional logic sentence
     */
    public static Node buildExpTree(List<String> expressions){
        Stack<Node> s = new Stack<>();

        for(String op : expressions){
            //If this is a negation, grab its single child from the stack
            if(op.equals("~")){
                s.push(new Node(op, s.pop(), null));
            }

            //If this is an operator, grab its two children from the stack
            else if(operators.contains(op)){
                Node right = s.pop();
                Node left = s.pop();
                s.push(new Node(op, left, right));
            }

            //If this is an atom, push it to the stack
            else
                s.push(new Node(op));
        }


        //Return the root node
        return s.pop();
    }
}




