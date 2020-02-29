
public class Main {

    public static void main(String[] args) {

        /**
         * To change the database to connect to, edit the strings at the top of
         * Database.java
         */

        Database database = new Database();
        database.dropDB();
        database.createDB();

        CreateTables createTables = new CreateTables();
        InsertRecords insertRecords = new InsertRecords();
        Query query = new Query();
        ModifyRecords modifyRecords = new ModifyRecords();
        //DropTables dropTables = new DropTables();
    }
}
