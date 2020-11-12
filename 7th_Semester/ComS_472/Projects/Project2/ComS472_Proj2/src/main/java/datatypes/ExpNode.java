package datatypes;

public class ExpNode {
    public String value;
    public ExpNode left;
    public ExpNode right;

    public ExpNode(String value) {
        this(value, null, null);
    }

    public ExpNode(String value, ExpNode left, ExpNode right){
        this.value = value;
        this.left = left;
        this.right = right;

    }
}