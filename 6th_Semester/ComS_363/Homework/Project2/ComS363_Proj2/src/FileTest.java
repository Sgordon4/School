import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileTest {
    public static void main(String[] args){

        Table table = new Table();

        while(true) {
            Scanner userInput = new Scanner(System.in);

            System.out.println("Please enter a command: ");
            String input = userInput.nextLine();

            System.out.println("Input: "+input);

            Pattern p = Pattern.compile("^(\\w+)\\s(.+)");
            Matcher m = p.matcher(input);

            if (!m.find( )){
                continue;
            }

            String command = m.group(1);
            String parameters = m.group(2);
            System.out.println("Command: "+command);
            System.out.println("Parameters: "+parameters);

            switch(command){
                case "create": table.create(parameters);
                    break;
                case "header": table.header(parameters);
                    break;
                case "insert": table.insert(parameters);
                    break;
                case "display": table.display(parameters);
                    break;
                case "delete": table.delete(parameters);
                    break;
                case "search": table.search(parameters);
                    break;
                default:
                    System.out.println(command+" is not a valid command");
            }
        }
    }
}
