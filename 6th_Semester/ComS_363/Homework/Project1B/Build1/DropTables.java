import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTables {
    public DropTables(){
        Database database = new Database();
        database.openConnection();

        Connection conn = database.getConnection();


        //Creating tables
        System.out.println("Dropping tables...");
        try{
            Statement stmt  = conn.createStatement();
            String drop = "DROP TABLE IF EXISTS students, departments, degrees, courses, register, major, minor;";
            stmt.executeUpdate(drop);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        database.closeConnection();
    }
}
