import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DBMS {

    public static Table table = new Table();

    public static void main(String[] args){

        while(true) {
            Scanner userInput = new Scanner(System.in);

            System.out.println();
            System.out.println("Please enter a command: ");
            String input = userInput.nextLine();


            Pattern p = Pattern.compile("^(\\w+)\\s(.+)");
            Matcher m = p.matcher(input);

            if (!m.find( )){
                System.out.println(input+" is not a valid command");
                continue;
            }
            System.out.println();

            String command = m.group(1);
            String parameters = m.group(2);


            String[] params = parseParameters(parameters);

            String parsedParams = params[0];
            String tableName = params[1];

            changeTable(tableName);


            switch(command){
                case "create":
                    if(doesTableExist(tableName)) {
                        System.out.println("Table " + tableName + " already exists!");
                        continue;
                    }

                    createTable(tableName);
                    table.create();

                    break;

                case "header":
                    if(!doesTableExist(tableName)) {
                        System.out.println("Table " + tableName + " does not exist!");
                        continue;
                    }

                    table.header();
                    break;
                case "insert":
                    if(!doesTableExist(tableName)) {
                        System.out.println("Table " + tableName + " does not exist!");
                        continue;
                    }

                    table.insert();
                    break;
                case "display":
                    table.display(parsedParams);
                    break;
                case "delete": table.delete(parsedParams);
                    break;
                case "search": table.search(parsedParams);
                    break;
                default:
                    System.out.println(command+" is not a valid command");
            }
        }
    }


    public static void createTable(String fileName){
        File file = new File(fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file");
            e.printStackTrace();
        }
    }

    public static boolean doesTableExist(String tableName){
        File file = new File(tableName);
        return file.exists();
    }


    public static String[] parseParameters(String parameters){
        Pattern p = Pattern.compile("^(.+)\\s(.+)$");
        Matcher m = p.matcher(parameters);

        String[] params = new String[2];


        if (!m.find( )){
            //I assume there is only a tableName, meaning this command is either create, header, or insert
            params[0] = null;
            params[1] = parameters;
        }
        else{
            //Otherwise there are actual parameters to send to display, delete, or search
            String param1 = m.group(1);
            String tableName = m.group(2);

            params[0] = param1;
            params[1] = tableName;
        }

        return params;
    }



    public static void changeTable(String tableName){
        if(table.fileName.equals(tableName))
            return;

        //If we are referencing a new table...
        table.fileName = tableName;

        //Set everything to invalid values to notify future operations that they need to be re-parsed
        table.setAllDirty();
    }
}
