import datatypes.Clause;
import datatypes.ExpNode;
import datatypes.ConjunctiveNormalForm;
import datatypes.Literal;

import java.util.List;

public class TreeToCNF {


    public static ConjunctiveNormalForm recursiveCNFConvert(ExpNode node){
        if(node == null) return null;

        ConjunctiveNormalForm left = recursiveCNFConvert(node.left);
        ConjunctiveNormalForm right = recursiveCNFConvert(node.right);
        /*
        if (left!=null) {
            System.out.println(node.value);
            System.out.println(left);
            if (right != null) System.out.println(right);
            System.out.println("=------------------------=");
        }
         */

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

    public static ConjunctiveNormalForm And(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm anded = cnf1.clone();
        ConjunctiveNormalForm clone = cnf2.clone();
        anded.list.addAll(clone.list);


        return anded;
    }

    public static ConjunctiveNormalForm Implies(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm left = Neg(cnf1);
        ConjunctiveNormalForm right = cnf2.clone();

        left = Or(left, right);

        return left;
    }

    public static ConjunctiveNormalForm IFF(ConjunctiveNormalForm cnf1, ConjunctiveNormalForm cnf2){

        ConjunctiveNormalForm left = Implies(cnf1, cnf2);
        ConjunctiveNormalForm right = Implies(cnf2, cnf1);

        return And(left, right);
    }
}