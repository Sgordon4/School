import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Table {
    /** File in which this table is stored */
    public String fileName;

    public int numberOfAttributes;
    public int numberOfRecords;

    /** Array in which each attribute is stored */
    public Attribute[] attribute;

    /** Represents a singular record, used as a temporary value */
    public ArrayList<Record> record;


    public Table(){
        fileName = "";
        numberOfAttributes = -1;
        numberOfRecords = -1;
        attribute = null;
        record = null;
    }


    //==================================================================================================================

    public void create(){
        //Have the user create the header
        numberOfAttributes = 0;
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
            numberOfAttributes++;

            if(!response.equals("y"))
                break;
        }

        //Add the number of attributes to the front of the header, and the number of records to the end
        header = new StringBuilder("[" + numberOfAttributes + "]" + header + "[0]");


        writeToFile(header.toString());
    }

    //==================================================================================================================

    public void header(){

        //If we haven't already grabbed this table's header, grab it and store it
        if(attribute == null) {
            if(!refreshHeader()) return;
        }

        //Print the header
        System.out.println(attribute.length+" attributes");

        for (int i = 0; i < attribute.length; i++){
            Attribute attr = attribute[i];
            System.out.println("Attribute "+(i+1)+": "+attr.name+", "+attr.intToType(attr.type));
        }

        System.out.println(numberOfRecords+" records");

    }

    //==================================================================================================================

    public void insert(){
        //Update our stored header if needed
        if(attribute == null) {
            if(!refreshHeader()) return;
        }
        //Update our stored records if needed
        if(record == null) {
            if(!refreshRecords()) return;
        }


        Scanner userInput = new Scanner(System.in);


        String[] values = new String[numberOfAttributes];
        for(int i = 0; i < attribute.length; i++){
            Attribute attr = attribute[i];

            System.out.print(attr.name+": ");
            String input = userInput.nextLine();

            if(input.contains("{") || input.contains("|") || input.contains("}")){
                System.out.println("Input cannot contain the characters '{', '|', '}'");
                i--;
                continue;
            }
            if(!attr.checkCorrectType(input, attr.type)){
                i--;
                continue;
            }

            values[i] = input;
        }

        //Add the record to the list
        record.add(new Record(values));
        numberOfRecords++;

        //Update the file with the new values
        rewriteFile();

    }

    //==================================================================================================================

    public void display(String parameters){
        //Update our stored header if needed
        if(attribute == null) {
            if(!refreshHeader()) return;
        }
        //Update our stored records if needed
        if(record == null) {
            if(!refreshRecords()) return;
        }

        int rid = Integer.parseInt(parameters);

        //Grab the record
        Record rec = record.get(rid);
        String[] values = rec.value;

        //Print the record
        for(int i = 0; i < numberOfAttributes; i++){
            System.out.print(attribute[i].name);
            System.out.print(": ");
            System.out.println(values[i]);
        }
    }

    //==================================================================================================================

    public void delete(String parameters){
        //Update our stored header if needed
        if(attribute == null) {
            if(!refreshHeader()) return;
        }
        //Update our stored records if needed
        if(record == null) {
            if(!refreshRecords()) return;
        }

        int rid = Integer.parseInt(parameters);

        //Delete the record
        record.remove(rid);
        numberOfRecords--;

        //Update the file with the new values
        rewriteFile();
    }

    //==================================================================================================================

    public void search(String condition){
        //Update our stored header if needed
        if(attribute == null) {
            if(!refreshHeader()) return;
        }
        //Update our stored records if needed
        if(record == null) {
            if(!refreshRecords()) return;
        }


        /*
        ^(")?(\w+)\s*=\s*(.+)(?(1)\1|)$
        ^(")?(\w+)\s*=\s*(.+)\1$
         */


        //Split up the condition
        Pattern p = Pattern.compile("^(\"?)(\\w+)\\s*=\\s*(.+)\\1$");
        Matcher m = p.matcher(condition);

        if (!m.find( )){
            System.out.println(condition+" is not a valid condition");
            return;
        }

        String attr = m.group(2);
        String value = m.group(3);

        //Find which attribute we're matching to
        int attributeNum;
        for(attributeNum = 0; attributeNum < numberOfAttributes; attributeNum++){
            String a = attribute[attributeNum].name;
            if(attr.equals(a)){
                break;
            }
        }
        if(attributeNum >= numberOfAttributes){
            System.out.println(attr+" is not a valid atribute for "+fileName);
            return;
        }


        //Search for all matching records
        ArrayList<Record> results = new ArrayList<>();

        for(int i = 0; i < numberOfRecords; i++){
            Record rec = record.get(i);

            if(value.equals(rec.value[attributeNum])){
                results.add(rec);
            }
        }


        //Print all matching records
        for(Record rec : results){
            String[] values = rec.value;

            //Print the record
            for(int i = 0; i < numberOfAttributes; i++){
                System.out.print(attribute[i].name);
                System.out.print(": ");
                System.out.println(values[i]);
            }

            System.out.println();
        }

    }

    //==================================================================================================================



    /**
     * Updates the file with all new information.
     * This method assumes refreshHeader() and refreshRecords() have been called recently
     */
    public void rewriteFile(){

        truncateFile();

        String header = formatHeader();
        writeToFile(header);

        for(Record rec : record){
            String formattedRec = formatRecord(rec);
            writeToFile(formattedRec);
        }
    }


    /**
     * Updates our stored table header information for the current table
     * @return True for success, False for failure
     */
    public boolean refreshHeader(){
        //Grab the header
        File file = new File(fileName);
        String header = null;

        Scanner reader;
        try{
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("There was an error finding the file!");
            e.printStackTrace();
            return false;
        }

        header = reader.nextLine();
        reader.close();



        //Split the header into parts
        String[] attributes = header.replaceAll("[\\[ ]+", "").split("]");

        //Grab important information
        numberOfAttributes = Integer.parseInt(attributes[0]);
        numberOfRecords = Integer.parseInt(attributes[attributes.length-1]);

        //Make the attribute array
        attribute = new Attribute[numberOfAttributes];


        //Split each attribute into parts and insert them into attributes
        for (int i = 0; i < attributes.length-2; i++) {
            String[] attr = attributes[i+1].split(":");

            String attrName = attr[0];
            int attrType = Integer.parseInt(attr[1]);

            attribute[i] = new Attribute(attrName, attrType);
        }

        return true;
    }


    /**
     * Updates our stored table header information for the current table.
     * This method assumes the header is refreshed
     * @return True for success, False for failure
     */
    public boolean refreshRecords(){
        File file = new File(fileName);

        Scanner reader;
        try{
            reader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("There was an error finding the file!");
            e.printStackTrace();
            return false;
        }

        //Dispose of the header
        reader.nextLine();

        //Grab and parse every record
        record = new ArrayList<>();
        numberOfRecords = 0;

        while(reader.hasNextLine()){
            String data = reader.nextLine();

            //Remove the leading and trailing brackets ({})
            data = data.substring(1, data.length()-1);

            //Alright, break it up, break it up...
            Record rec = new Record(data.split("\\|"));

            //Add the record to the pile
            record.add(rec);
            numberOfRecords++;
        }

        reader.close();

        return true;
    }


    /**
     * Takes the stored header and formats it for future storage
     * This method assumes refreshRecords() has been called recently to update numberOfRecords
     * @return Correctly formatted header as a String
     */
    public String formatHeader(){
        StringBuilder header = new StringBuilder();
        header.append("[").append(numberOfAttributes).append("]");

        for(Attribute attr : attribute){
            header.append("[").append(attr.name).append(":").append(attr.type).append("]");
        }

        header.append("[").append(numberOfRecords).append("]");

        return header.toString();
    }
    /**
     * Takes the passed record and formats it for future storage
     * @return Correctly formatted record as a String
     */
    public String formatRecord(Record record){
        StringBuilder rec = new StringBuilder("{");
        for(String data : record.value){
            rec.append(data).append("|");
        }

        rec.setLength(rec.length() - 1);
        rec.append("}");

        return rec.toString();
    }


    public void writeToFile(String text){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(text).append("\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the file");
            e.printStackTrace();
        }
    }

    public void truncateFile(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets every global var to invalid values to notify future operations that they need to be updated
     */
    public void setAllDirty(){
        numberOfAttributes = -1;
        numberOfRecords = -1;
        attribute = null;
        record = null;
    }
}
