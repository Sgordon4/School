import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table2 {
    /** File in which this table is stored */
    public String fileName;

    public int numberOfAttributes;
    /** Array in which each attribute is stored */
    public Attribute[] attribute;

    /** Represents a singular record, used as a temporary value */
    public Record[] record;


    //------------------------------------------------------------------------------------------------------------------

    public void create(String parameters){
        //'parameters' is effectively the fileName

        changeFile(parameters);
        File file = new File(fileName);

        try {
            if (!file.createNewFile()) {
                System.out.println("File already exists!");
                return;
            }
        } catch (IOException e) {
            System.out.println("An error occurred when creating the file");
            e.printStackTrace();
        }


        //Have the user create the header
        int numAttributes = 0;
        StringBuilder header = new StringBuilder();

        Scanner userInput = new Scanner(System.in);
        while(true) {
            System.out.println("Attribute name:");
            String attributeName = userInput.nextLine();

            System.out.println("Select a type: 1. integer; 2. double; 3. boolean; 4. string");
            int type = Integer.parseInt(userInput.nextLine());

            if(type < 1 || type > 4){
                System.out.println(type+" is not a valid type");
            }

            System.out.println("More attribute? (y/n)");
            String response = userInput.nextLine();

            header.append("[").append(attributeName).append(":").append(type).append("]");
            numAttributes++;

            if(!response.equals("y"))
                break;
        }

        //Add the number of attributes to the front of the header, and the number of records to the end
        header = new StringBuilder("[" + numAttributes + "]" + header + "[0]");

        System.out.println("Header: \n" + header);
    }

    //------------------------------------------------------------------------------------------------------------------

    public void header(String parameters){
        //'parameters' is effectively the table name
        changeFile(parameters);

        File file = new File(fileName);
        if(!file.exists()){
            System.out.println("File "+parameters+" does not exist!");
            return;
        }

        //Split the header into parts
        String[] split = parameters.split("\\[");
        //This regex will leave the ending bracket from each attribute, I'm just removing them in post

        //Print attributes
        System.out.println(split[0].substring(0, split[0].length() - 1) + " attributes");
        for(int i = 1; i < split.length - 1; i++){
            String attribute = split[i].substring(0, split[i].length() - 1);

            String attributeName = attribute.substring(0, attribute.length() - 2);
            int attributeType = Integer.parseInt(attribute.substring(attribute.length() - 1, attribute.length()));

            String type = "unknown";
            switch (attributeType){
                case 1: type = "integer";
                    break;
                case 2: type = "double";
                    break;
                case 3: type = "boolean";
                    break;
                case 4: type = "string";
                    break;
            }

            System.out.println("Attribute "+i+": "+attributeName+", "+type);
        }

        System.out.println("This table has "+ split[split.length-1].substring(0, split[split.length-1].length() - 1) +" records");

    }

    //------------------------------------------------------------------------------------------------------------------

    public void insert(String parameters){
        //'parameters' is effectively the table name

        //TODO prompt for shit
    }

    //------------------------------------------------------------------------------------------------------------------

    public void display(String parameters){
        Pattern p = Pattern.compile("^(.+)\\s(.+)$");
        Matcher m = p.matcher(parameters);

        if (!m.find( )){
            System.out.println("Error: "+parameters+" are not valid parameters for 'display'");
            return;
        }

        int rid = Integer.parseInt(m.group(1));
        String tableName = m.group(2);

    }

    //------------------------------------------------------------------------------------------------------------------

    public void delete(String parameters){
        Pattern p = Pattern.compile("^(.+)\\s(.+)$");
        Matcher m = p.matcher(parameters);

        if (!m.find( )){
            System.out.println("Error: "+parameters+" are not valid parameters for 'display'");
            return;
        }

        int rid = Integer.parseInt(m.group(1));
        String tableName = m.group(2);

    }

    //------------------------------------------------------------------------------------------------------------------

    public void search(String parameters){
        Pattern p = Pattern.compile("^(.+)\\s(.+)$");
        Matcher m = p.matcher(parameters);

        if (!m.find( )){
            System.out.println("Error: "+parameters+" are not valid parameters for 'display'");
            return;
        }

        int rid = Integer.parseInt(m.group(1));
        String condition = m.group(2);

    }

    //------------------------------------------------------------------------------------------------------------------

    public void writeToFile(String text){

        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(text);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file");
            e.printStackTrace();
        }
    }

    public void changeFile(String fileName){
        this.fileName = fileName;
    }
}
