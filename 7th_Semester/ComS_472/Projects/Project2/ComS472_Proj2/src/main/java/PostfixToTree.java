import datatypes.ExpNode;
import java.util.*;

public class PostfixToTree {
    static Set<String> operators = new HashSet<String>(Arrays.asList("~", "&&", "||", "<=>", "=>", "("));

    //https://stackoverflow.com/questions/423898/postfix-notation-to-expression-tree
    public static ExpNode buildExpTree(List<String> expressions){
        Stack<ExpNode> s = new Stack<>();

        for(String op : expressions){
            //If this is a negation, grab its single child from the stack
            if(op.equals("~")){
                s.push(new ExpNode(op, s.pop(), null));
            }

            //If this is an operator, grab its two children from the stack
            else if(operators.contains(op)){
                ExpNode right = s.pop();
                ExpNode left = s.pop();
                s.push(new ExpNode(op, left, right));
            }

            //If this is an atom, push it to the stack
            else
                s.push(new ExpNode(op));
        }


        //Return the root node
        return s.pop();
    }
}




