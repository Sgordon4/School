import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Query {

    static DatabaseConn database;
    static Connection connection;
    public static void main(String[] args) {
        database = new DatabaseConn();
        database.openConnection();

        connection = database.getConnection();

        Query(connection);

        database.closeConnection();
    }
    public static void Query(Connection conn){

        System.out.println("Querying tables...");
        try{
            Statement stmt  = conn.createStatement();
            ResultSet rs;

            String query = "SELECT snum, ssn FROM students WHERE name='Becky';";
            rs = stmt.executeQuery(query);
            System.out.println("\n1)");
            while(rs.next()){
                System.out.println("Number: "+rs.getString("snum")+", SSN: "+rs.getString("ssn"));
            }

            //-----------------------------------------------------

            query = "SELECT name, level FROM major WHERE snum = (SELECT snum FROM students WHERE ssn = '123097834');";
            rs = stmt.executeQuery(query);
            System.out.println("\n2)");
            while(rs.next()){
                System.out.println("Name: "+rs.getString("name")+", Level: "+rs.getString("level"));
            }

            //-----------------------------------------------------

            query = "SELECT DISTINCT name FROM courses WHERE department_code = (SELECT code FROM departments WHERE name = 'Computer Science');";
            rs = stmt.executeQuery(query);
            System.out.println("\n3)");
            while(rs.next()){
                System.out.println("Name: "+rs.getString("name"));
            }

            //-----------------------------------------------------

            query = "SELECT DISTINCT name, level FROM degrees WHERE department_code = (SELECT code FROM departments WHERE name = 'Computer Science');";
            rs = stmt.executeQuery(query);
            System.out.println("\n4)");
            while(rs.next()){
                System.out.println("Name: "+rs.getString("name")+", Level: "+rs.getString("level"));
            }

            //-----------------------------------------------------

            query = "SELECT DISTINCT name FROM students WHERE snum IN (SELECT DISTINCT snum FROM minor);";
            rs = stmt.executeQuery(query);
            System.out.println("\n5)");
            while(rs.next()){
                System.out.println("Name: "+rs.getString("name"));
            }

            //-----------------------------------------------------

            System.out.println("\n");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
