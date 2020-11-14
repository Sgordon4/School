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



    //https://stackoverflow.com/a/50078401
    public void printPretty(){
        System.out.println(this.value);

        if(this.left != null)
            this.left.printHelper("", this.right == null);
        if(this.right != null)
            this.right.printHelper("", true);
    }

    public void printHelper(String prefix, boolean isTail){
        System.out.println(prefix + (isTail ? "└── " : "├── ") + this.value);

        if(this.left != null)
            this.left.printHelper(prefix + (isTail ? "    " : "│   "), this.right == null);
        if(this.right != null)
            this.right.printHelper(prefix + (isTail ?"    " : "│   "), true);
    }
}