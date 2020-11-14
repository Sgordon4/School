package datatypes;

import java.util.LinkedList;
import java.util.List;

public class ConjunctiveNormalForm {
    public List<Clause> list = new LinkedList<>();


    @Override
    public ConjunctiveNormalForm clone(){
        ConjunctiveNormalForm cnf = new ConjunctiveNormalForm();

        for(Clause clause : this.list){
            cnf.list.add(clause.clone());
        }

        return cnf;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("(");
        for(Clause clause : this.list){
            ret.append("(");
            ret.append(clause.toString());
            ret.append(")");
            ret.append(" && ");
        }
        ret = new StringBuilder(ret.substring(0, ret.length() - 4)); //Remove last &&
        ret.append(")");

        return ret.toString();
    }

    public String printStructure(){
        StringBuilder ret = new StringBuilder("\n\n-------------\n");
        for(Clause clause : this.list){
            //ret.append("[] ");
            for(Literal lit : clause.list){
                ret.append(lit.toString());
                ret.append("  ");
            }
            ret = new StringBuilder(ret.substring(0, ret.length() - 2)); //Remove last two spaces
            ret.append("\n");
        }
        ret.append("-------------\n\n");
        return ret.toString();
    }
}
