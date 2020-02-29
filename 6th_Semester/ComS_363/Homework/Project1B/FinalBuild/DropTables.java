import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTables {

    static DatabaseConn database;
    static Connection connection;
    public static void main(String[] args) {
        database = new DatabaseConn();
        database.openConnection();

        connection = database.getConnection();

        DropTables(connection);

        database.closeConnection();
    }
    public static void DropTables(Connection conn){

        System.out.println("Dropping tables...");
        try{
            Statement stmt  = conn.createStatement();
            String drop = "DROP TABLE IF EXISTS students, departments, degrees, courses, register, major, minor;";
            stmt.executeUpdate(drop);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
