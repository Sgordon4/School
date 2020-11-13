import datatypes.ExpNode;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String sample1 = "~(P && ~Q) || R => S && ~T";
        String sample2 = "(Rain && Outside) => Wet";
        String sample3 = "A && (~B || C || (~D => ~E)) <=> ((F && G))";
        String sample4 = "~(P&&~Q)||R=>S&&~T";


        String pattern = "(\\()|(\\))|(<=>)|(=>)|(\\|\\|)|(&&)|(~)|(\\w+)";
        Matcher m2 = Pattern.compile(pattern).matcher(sample4); //Match all individual parts

        List<String> allMatches = new ArrayList<String>();
        while (m2.find()) {
            allMatches.add(m2.group());
        }


        //Build an expression tree from the parsed operators and operands
        List<String> postfix = ExpressionToPostfix.infixToPostfix(allMatches);
        ExpNode expressionTree = PostfixToTree.buildExpTree(postfix);


    }
}