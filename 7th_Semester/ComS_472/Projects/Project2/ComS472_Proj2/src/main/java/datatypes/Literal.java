package datatypes;

public class Literal {
    public String literal;
    public boolean negated = false;

    public Literal(String literal){
        this.literal = literal;
    }

    @Override
    public Literal clone() {
        Literal literal = new Literal(this.literal);
        literal.negated = this.negated;
        return literal;
    }

    @Override
    public String toString() {
        return this.negated ? "~"+this.literal : this.literal;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass())
            return super.equals(obj);

        Literal toCompare = (Literal) obj;
        return (this.literal.equals(toCompare.literal) && this.negated == toCompare.negated);
    }
    public boolean equalsNegated(Literal lit){
        return (this.literal.equals(lit.literal) && this.negated != lit.negated);
    }
}
