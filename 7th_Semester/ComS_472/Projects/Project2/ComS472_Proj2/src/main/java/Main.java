import datatypes.Clause;
import datatypes.ConjunctiveNormalForm;

public class Main {
    public static void main(String[] args) {

        ConjunctiveNormalForm cnf1 = Resolver.stringToCNF("~Rain || ~Outside || Wet");
        ConjunctiveNormalForm cnf2 = Resolver.stringToCNF("~Warm || Rain || Pleasant");
        ConjunctiveNormalForm cnf3 = Resolver.stringToCNF("~Wet");
        ConjunctiveNormalForm cnf4 = Resolver.stringToCNF("Outside");
        ConjunctiveNormalForm cnf5 = Resolver.stringToCNF("Warm");

        ConjunctiveNormalForm KB = new ConjunctiveNormalForm();
        KB.list.addAll(cnf1.clone().list);
        KB.list.addAll(cnf2.clone().list);
        KB.list.addAll(cnf3.clone().list);
        KB.list.addAll(cnf4.clone().list);
        KB.list.addAll(cnf5.clone().list);


        System.out.println();
        System.out.println("knowledge base in clauses:");
        System.out.println(KB.printStructure());



        ConjunctiveNormalForm modifiedKB = KB.clone();

        ConjunctiveNormalForm goalCNF = Resolver.stringToCNF("Pleasant");
        Clause goalClause = goalCNF.list.get(0); //Goal clause

        System.out.println("****************");
        System.out.println("Goal sentence 1:");
        System.out.println(goalClause);
        System.out.println("****************");
        System.out.println();

        ConjunctiveNormalForm negGoalCNF = TreeToCNF.Neg(goalCNF.clone());     //Negate goal clause
        Clause negGoalClause = negGoalCNF.list.get(0);
        System.out.println("Negated goal in clauses:");
        System.out.println(negGoalClause);
        System.out.println();

        System.out.println("Proof by refutation:");
        System.out.println();
        if(Resolver.resolveKBWithClause(modifiedKB, negGoalClause))
            System.out.println("The KB entails "+goalClause);
        else{
            System.out.println("No new clauses are added.");
            System.out.println("The KB does not entail "+goalClause);
        }
        System.out.println();




        /*
        ConjunctiveNormalForm modifiedKB2 = KB.clone();

        ConjunctiveNormalForm cnf7 = Resolution.stringToCNF("Rain");   //Goal sentence
        cnf7 = TreeToCNF.Neg(cnf7.clone());     //Negate goal sentence
        Clause current2 = cnf7.list.get(0);

        System.out.println("Resolved?");
        System.out.println(Resolution.resolveKBWithClause(modifiedKB2, current2));

         */
    }
}
