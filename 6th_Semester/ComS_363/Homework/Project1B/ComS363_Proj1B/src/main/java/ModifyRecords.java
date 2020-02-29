import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRecords {

    static DatabaseConn database;
    static Connection connection;
    public static void main(String[] args) {
        database = new DatabaseConn();
        database.openConnection();

        connection = database.getConnection();

        ModifyRecords(connection);

        database.closeConnection();
    }
    public static void ModifyRecords(Connection conn){

        System.out.println("Modifying records...");
        try{
            Statement stmt  = conn.createStatement();
            String change = "UPDATE students SET name = \"Scott\" WHERE ssn = \"746897816\";";
            stmt.executeUpdate(change);

            //-----------------------------------------------------

            change = "UPDATE major SET name = \"Computer Science\", level = \"MS\" WHERE snum = (SELECT snum FROM students WHERE ssn = \"746897816\");";
            stmt.executeUpdate(change);

            //-----------------------------------------------------

            change = "DELETE FROM register WHERE regtime = \"Spring2015\";";
            stmt.executeUpdate(change);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
