import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    static DatabaseConn database;
    static Connection connection;
    public static void main(String[] args) {
        database = new DatabaseConn();
        database.openConnection();

        connection = database.getConnection();

        CreateTables(connection);

        database.closeConnection();
    }
    public static void CreateTables(Connection conn){

        System.out.println();
        System.out.println("Creating tables...");
        try{
            Statement stmt  = conn.createStatement();

            String students = "CREATE TABLE IF NOT EXISTS students ( " +
                    "snum INTEGER," +
                    "ssn INTEGER," +
                    "name VARCHAR(10)," +
                    "gender VARCHAR(1)," +
                    "dob date," +
                    "c_addr VARCHAR(20)," +
                    "c_phone VARCHAR(10)," +
                    "p_addr VARCHAR(20)," +
                    "p_phone VARCHAR(10)," +
                    "PRIMARY KEY (ssn)," +
                    "UNIQUE (snum)" +
                    ");";
            stmt.executeUpdate(students);

            //-----------------------------------------------------

            String departments = "CREATE TABLE IF NOT EXISTS departments (" +
                    "code INTEGER," +
                    "name VARCHAR(50)," +
                    "phone VARCHAR(10)," +
                    "college VARCHAR(20)," +
                    "PRIMARY KEY (code)," +
                    "UNIQUE (name)" +
                    ");";
            stmt.executeUpdate(departments);

            //-----------------------------------------------------

            String degrees = "CREATE TABLE IF NOT EXISTS degrees (" +
                    "name VARCHAR(50)," +
                    "level VARCHAR(5)," +
                    "department_code INTEGER," +
                    "PRIMARY KEY (name , level)," +
                    "FOREIGN KEY (department_code)" +
                    "    REFERENCES departments (code)" +
                    ");";
            stmt.executeUpdate(degrees);

            //-----------------------------------------------------

            String courses = "CREATE TABLE IF NOT EXISTS courses (" +
                    "number INTEGER," +
                    "name VARCHAR(50)," +
                    "description VARCHAR(50)," +
                    "credithours INTEGER," +
                    "level VARCHAR(20)," +
                    "department_code INTEGER," +
                    "PRIMARY KEY (number)," +
                    "UNIQUE (name)," +
                    "FOREIGN KEY (department_code)" +
                    "    REFERENCES departments (code)" +
                    ");";
            stmt.executeUpdate(courses);

            //-----------------------------------------------------

            String register = "CREATE TABLE IF NOT EXISTS register (" +
                    "snum integer," +
                    "course_number integer," +
                    "regtime VARCHAR(20)," +
                    "grade INTEGER," +
                    "PRIMARY KEY (snum , course_number)," +
                    "FOREIGN KEY (snum)" +
                    "    REFERENCES students (snum)" +
                    ");";
            stmt.executeUpdate(register);

            //-----------------------------------------------------

            String major = "CREATE TABLE IF NOT EXISTS major (" +
                    "snum INTEGER," +
                    "name VARCHAR(50)," +
                    "level VARCHAR(5)," +
                    "PRIMARY KEY (snum , name , level)," +
                    "FOREIGN KEY (snum)" +
                    "    REFERENCES students (snum)," +
                    "FOREIGN KEY (name , level)" +
                    "    REFERENCES degrees (name , level)" +
                    ");";
            stmt.executeUpdate(major);

            //-----------------------------------------------------

            String minor = "CREATE TABLE IF NOT EXISTS minor (" +
                    "snum INTEGER," +
                    "name VARCHAR(50)," +
                    "level VARCHAR(5)," +
                    "PRIMARY KEY (snum , name , level)," +
                    "FOREIGN KEY (snum) REFERENCES students (snum)," +
                    "FOREIGN KEY (name, level) REFERENCES degrees (name, level)" +
                    ");";
            stmt.executeUpdate(minor);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
