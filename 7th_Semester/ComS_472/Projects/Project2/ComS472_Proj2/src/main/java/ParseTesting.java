import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTesting {

    Stack stack;


    static String pattern = "(\\()|(\\))|(<=>)|(=>)|(\\|\\|)|(&&)|(~)|(\\w+)";
    static String sample1 = "~(P && ~Q) || R => S && ~T";
    static String sample2 = "(Rain && Outside) => Wet";
    static String sample3 = "A && (~B || C || (~D => ~E)) <=> ((F && G))";


    public static void main(String[] args){
        List<String> allMatches = new ArrayList<String>();
        Matcher m2 = Pattern.compile(pattern).matcher(sample1);
        while (m2.find()) {
            allMatches.add(m2.group());
        }

        for(String x : allMatches){
            System.out.println(x);
        }

    }


    int opPrecedence(String op){
        switch (op){

        }
        return 0;
    }


}
