package datatypes;

public class Node {
    public String value;
    public Node left;
    public Node right;

    public Node(String value) {
        this(value, null, null);
    }

    public Node(String value, Node left, Node right){
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