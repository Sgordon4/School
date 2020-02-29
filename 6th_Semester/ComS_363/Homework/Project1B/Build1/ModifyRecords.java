import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ModifyRecords {
    public ModifyRecords(){
        Database database = new Database();
        database.openConnection();

        Connection conn = database.getConnection();


        //Creating tables
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

        database.closeConnection();
    }
}
