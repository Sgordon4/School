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
}
