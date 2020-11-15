import datatypes.Clause;
import datatypes.ConjunctiveNormalForm;
import datatypes.Node;
import datatypes.Literal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resolver {
    /**
     * Given a sentence string (e.g. "( Rain && Outside ) => Wet"), parses it into tokens and, using
     * ExpressionToPostfix.infixToPostfix and PostfixToTree.buildExpTree, builds an expression tree.
     * The expression tree is then sent to TreeToCNF.recursiveCNFConvert to convert to CNF.
     *
     * @param str Sentence string to parse and convert to CNF
     * @return CNF version of str
     */
    public static ConjunctiveNormalForm stringToCNF(String str){
        String pattern = "(\\()|(\\))|(<=>)|(=>)|(\\|\\|)|(&&)|(~)|(\\w+)";
        Matcher m2 = Pattern.compile(pattern).matcher(str); //Match all individual parts

        List<String> allMatches = new ArrayList<String>();
        while (m2.find()) {
            allMatches.add(m2.group());
        }

        //Build an expression tree from the parsed operators and operands
        List<String> postfix = ExpressionToPostfix.infixToPostfix(allMatches);
        Node expressionTree = PostfixToTree.buildExpTree(postfix);

        ConjunctiveNormalForm cnf = TreeToCNF.recursiveCNFConvert(expressionTree);


        //Printing these variables for testing can be done as so:
        //expressionTree.printPretty();
        //System.out.println(cnf);
        //System.out.println(cnf.printStructure());

        return cnf;
    }

    /**
     * Given a knowledge base and a clause to prove, resolve the clause with the knowledge base until a conclusion
     * is reached.
     *
     * @param KB            Knowledge Base containing clauses to resolve
     * @param current       Clause to be proven using KB
     * @param returnString  StringBuilder to print project required output to
     * @return boolean True if the clause can be proven, False if not
     */
    public static boolean proveClauseWithKB(ConjunctiveNormalForm KB, Clause current, StringBuilder returnString){

        //For all untouched clauses in the knowledge base
        for(int i=0; i < KB.list.size(); i++){
            Clause toCompare = KB.list.get(i);

            //For every literal in this clause...
            for(Literal literal : current.list) {
                if (toCompare.containsNeg(literal)) {   //If we found a match...

                    KB.list.remove(toCompare);          //Remove the clause from the KB
                    i = -1;                             //Set to restart from the top of the list


                    returnString.append(current).append("\n");
                    returnString.append(toCompare).append("\n");
                    returnString.append("--------------------").append("\n");

                    current.list.addAll(toCompare.clone().list);    //Combine the clauses

                    //Remove the complimentary literals (A || ~A)
                    Literal lit = literal.clone();
                    current.list.remove(lit);
                    lit.negated = !lit.negated;
                    current.list.remove(lit);

                    returnString.append(current).append("\n\n");

                    if(current.list.size() == 0)
                        return true;
                    break;
                }
            }
        }
        return false;
    }
}
