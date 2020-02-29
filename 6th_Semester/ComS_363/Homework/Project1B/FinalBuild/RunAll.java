import java.sql.Connection;

public class RunAll {

    public static void main(String[] args) {

        /**
         * To change the database to connect to, edit the strings at the top of
         * DatabaseConn.java
         */

        DatabaseConn database = new DatabaseConn();
        database.dropDB();
        database.createDB();

        database.openConnection();
        Connection conn = database.getConnection();


        CreateTables.CreateTables(conn);
        InsertRecords.InsertRecords(conn);
        Query.Query(conn);
        ModifyRecords.ModifyRecords(conn);
        //DropTables.DropTables(conn);


        database.closeConnection();
    }
}
