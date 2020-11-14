package datatypes;

import java.util.LinkedList;
import java.util.List;

public class Clause {
    public List<Literal> list = new LinkedList<>();

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass())
            return super.equals(obj);

        return list.containsAll(((Clause) obj).list);
    }

    public boolean containsNeg(Literal literal) {
        for(Literal lit : this.list){
            if(lit.literal.equals(literal.literal) && lit.negated != literal.negated)
                return true;
        }
        return false;
    }

    @Override
    public Clause clone(){
        Clause clause = new Clause();

        for(Literal lit : this.list){
            clause.list.add(lit.clone());
        }

        return clause;
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(Literal lit : this.list){
            ret.append(lit.toString());
            ret.append(" || ");
        }
        ret = new StringBuilder(ret.substring(0, ret.length() - 4)); //Remove last ||

        return ret.toString();
    }
}
