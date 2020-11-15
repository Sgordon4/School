import datatypes.Clause;
import datatypes.ConjunctiveNormalForm;
import datatypes.ExpNode;
import datatypes.Literal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resolver {
    public static ConjunctiveNormalForm stringToCNF(String str){
        String pattern = "(\\()|(\\))|(<=>)|(=>)|(\\|\\|)|(&&)|(~)|(\\w+)";
        Matcher m2 = Pattern.compile(pattern).matcher(str); //Match all individual parts

        List<String> allMatches = new ArrayList<String>();
        while (m2.find()) {
            allMatches.add(m2.group());
        }

        //Build an expression tree from the parsed operators and operands
        List<String> postfix = ExpressionToPostfix.infixToPostfix(allMatches);
        ExpNode expressionTree = PostfixToTree.buildExpTree(postfix);

        //expressionTree.printPretty();
        ConjunctiveNormalForm cnf = TreeToCNF.recursiveCNFConvert(expressionTree);
        //System.out.println(cnf);
        //System.out.println(cnf.printStructure());

        return cnf;
    }

    public static boolean resolveKBWithClause(ConjunctiveNormalForm KB, Clause current){

        //For all untouched clauses in the knowledge base
        for(int i=0; i < KB.list.size(); i++){
            Clause toCompare = KB.list.get(i);

            //For every literal in this clause...
            for(Literal literal : current.list) {
                if (toCompare.containsNeg(literal)) {

                    KB.list.remove(toCompare);
                    i = -1; //Restart from the top of the list


                    System.out.println(current);
                    System.out.println(toCompare);
                    System.out.println("--------------------");

                    current.list.addAll(toCompare.clone().list);
                    current.removeConflictingLiteral(literal);

                    System.out.println(current);
                    System.out.println();

                    if(current.list.size() == 0)
                        return true;
                    break;
                }
            }
        }
        return false;
    }
}
