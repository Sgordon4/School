package edu.iastate.cs472.proj2;

import edu.iastate.cs472.proj2.datatypes.Clause;
import edu.iastate.cs472.proj2.datatypes.Node;
import edu.iastate.cs472.proj2.datatypes.ConjunctiveNormalForm;
import edu.iastate.cs472.proj2.datatypes.Literal;

import java.util.List;

/**
 * @author Sean Gordon
 */
public class TreeToCNF {

    /**
     * Given a PL expression tree, recursively applies various transformations based on the parent node's operator
     *
     * @param node Expression tree of a propositional logic sentence
     * @return ConjunctiveNormalForm CNF representation of the given propositional logic sentence
     */
    public static ConjunctiveNormalForm recursiveCNFConvert(Node node){
        if(node == null) return null;

        ConjunctiveNormalForm left = recursiveCNFConvert(node.left);
        ConjunctiveNormalForm right = recursiveCNFConvert(node.right);


        //If both children are null, this is an atom
        if(left == null && right == null){
            Literal lit = new Literal(node.value);
            Clause clause = new Clause();
            clause.list.add(lit);
            ConjunctiveNormalForm conj = new ConjunctiveNormalForm();
            conj.list.add(clause);
            return conj;
        }


        //Apply conversion to CNF
        switch (node.value){
            case "~": return Neg(left);
            case "&&": return And(left, right);
            case "||": return Or(left, right);
            case "=>": return Implies(left, right);
            case "<=>": return IFF(left, right);
            default: return null;
        }
    }


    /**
     * Given a sentence in CNF, negate it
     * A  becomes  ~A
     *
     * @param toNegate Sentence in CNF to negate
     * @return ConjunctiveNormalForm Negated version of the input sentence, still in CNF
     */
    public static ConjunctiveNormalForm Neg(ConjunctiveNormalForm toNegate){

        List<Clause> toNegateList = toNegate.list;

        ConjunctiveNormalForm negated = new ConjunctiveNormalForm();
        for(Literal lit : toNegateList.get(0).list){
            Literal newLit = lit.clone();
            newLit.negated = !newLit.negated;

            Clause newClause = new Clause();
            newClause.list.add(newLit);
            negated.list.add(newClause);
        }


        //Skip first
        for(Clause clause : toNegateList.subList(1, toNegateList.size())){
            //Turn its insides into ands
            ConjunctiveNormalForm toOr = new ConjunctiveNormalForm();
            for(Literal lit : clause.list){
                Literal newLit = lit.clone();
                newLit.negated = !newLit.negated;

                Clause newClause = new Clause();
                newClause.list.add(newLit);
                toOr.list.add(newClause);
            }

            negated = Or(negated, toOr);
        }

        return negated;
    }


    /**
     * Given two sentences in CNF, OR them together and distribute as necessary to remain in CNF
     * (A)  and  (B)  become  (A || B)
     *
     * @param cnf1 Sentence 1 in CNF to combine
     * @param cnf2 Sentence 2 in CNF to combine
     * @return ConjunctiveNormalForm ORed version of the input sentences, still in CNF
     */
    public static ConjunctiveNormalForm Or(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm ored = new ConjunctiveNormalForm();

        //For every clause in cnf1, apply each clause from cnf2
        for(Clause clause : cnf1.list){
            for(Clause clause2 : cnf2.list){

                Clause newClause = clause.clone();
                newClause.list.addAll(clause2.list);

                ored.list.add(newClause);
            }
        }

        return ored;
    }

    /**
     * Given two sentences in CNF, AND them together
     * (A)  and  (B)  become  (A && B)
     *
     * @param cnf1 Sentence 1 in CNF to combine
     * @param cnf2 Sentence 2 in CNF to combine
     * @return ConjunctiveNormalForm ANDed version of the input sentences, still in CNF
     */
    public static ConjunctiveNormalForm And(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm anded = cnf1.clone();
        ConjunctiveNormalForm clone = cnf2.clone();
        anded.list.addAll(clone.list);


        return anded;
    }

    /**
     * Given two sentences in CNF, one of which implies the other, break the implication into CNF
     * A => B  becomes  ~A || B
     *
     * @param cnf1 Sentence 1 in CNF that implies cnf2
     * @param cnf2 Sentence 2 in CNF that is implied by cnf1
     * @return ConjunctiveNormalForm CNF version of the input sentences
     */
    public static ConjunctiveNormalForm Implies(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm left = Neg(cnf1);
        ConjunctiveNormalForm right = cnf2.clone();

        left = Or(left, right);

        return left;
    }

    /**
     * Given two sentences in CNF, both of which implies the other, break the IFF into two implications
     * A <=> B  becomes  A => B  and  B => A
     *
     * @param cnf1 Sentence 1 in CNF that implies cnf2
     * @param cnf2 Sentence 2 in CNF that implies cnf1
     * @return ConjunctiveNormalForm CNF version of the input sentences
     */
    public static ConjunctiveNormalForm IFF(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm left = Implies(cnf1, cnf2);
        ConjunctiveNormalForm right = Implies(cnf2, cnf1);

        return And(left, right);
    }
}