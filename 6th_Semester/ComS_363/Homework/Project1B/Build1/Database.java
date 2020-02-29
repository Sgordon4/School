import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    String url;
    String userName;
    String password;

    String dbName;

    Connection conn = null;

    /**
     * To change the database to connect to, edit these strings
     */
    public Database(){
        url = "jdbc:mysql://127.0.0.1:3306";
        dbName = "coms363_proj1b";

        userName = "root";
        password = "root";
    }

    public void createDB(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);

            if(!conn.isValid(1000))
                throw new Exception("Connection is invalid");

            Statement stmt  = conn.createStatement();
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName + ";" ;
            stmt.executeUpdate(sql);

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void dropDB(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,userName,password);

            if(!conn.isValid(1000))
                throw new Exception("Connection is invalid");

            Statement stmt  = conn.createStatement();
            String sql = "DROP DATABASE IF EXISTS " + dbName + ";" ;
            stmt.executeUpdate(sql);

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openConnection(){
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url+"/"+dbName,userName,password);

            if(!conn.isValid(1000))
                throw new Exception("Connection is invalid");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try{
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return conn;
    }
}
