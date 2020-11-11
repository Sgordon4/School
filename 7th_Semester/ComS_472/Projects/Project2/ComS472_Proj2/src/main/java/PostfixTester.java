import java.util.Stack;

public class PostfixTester {


    public static void main(String[] args){
        String[] a = new String[]{"a", "*", "(", "b", "-", "c", ")", "^", "d"};

        Stack s = new Stack();
        String output = "";
        
    }





    int precedence(String thing){
        switch (thing){
            case "^": return 4;
            case "*": return 3;
            case "-": return 2;
            case "(": return 1;
            case ")": return 0;
            default: return -1;
        }
    }
}
