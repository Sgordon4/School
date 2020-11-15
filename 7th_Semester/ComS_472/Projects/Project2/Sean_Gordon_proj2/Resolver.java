package edu.iastate.cs472.proj2;

import edu.iastate.cs472.proj2.datatypes.Clause;
import edu.iastate.cs472.proj2.datatypes.ConjunctiveNormalForm;
import edu.iastate.cs472.proj2.datatypes.Literal;

/**
 * @author Sean Gordon
 */
public class Resolver {

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
