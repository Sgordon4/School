
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*, java.sql.*"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*"%>
<%!
    private static class Query {
        public String moveItMoveIt(){

            //------------------------------------------------------------------------
            // Establish Database Connection
            //------------------------------------------------------------------------

            String connectionURL = "jdbc:mysql://127.0.0.1:3306/coms363_proj1c";
            Connection connection = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");

                connection = DriverManager.getConnection(
                        connectionURL, "root", "root");

                if(!connection.isValid(1000))
                    throw new Exception("Connection is invalid");

            } catch (Exception e) {
                e.printStackTrace();
                return "Error creating database connection: \n"+ Arrays.toString(e.getStackTrace());
            }

            //------------------------------------------------------------------------
            // Query Tables
            //------------------------------------------------------------------------

            String ret = "1) \n";

            try{
                Statement stmt  = connection.createStatement();
                ResultSet rs;

                String query = "SELECT snum, ssn FROM students WHERE name='Becky';";
                rs = stmt.executeQuery(query);

                while(rs.next()){
                    ret += ("Number: "+rs.getString("snum")+", SSN: "+rs.getString("ssn"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                return "Error querying tables: \n"+ Arrays.toString(e.getStackTrace());
            }

            return ret;
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Query Result 1</title>
    </head>
    <body>
        <h1>This is the result of query 1.</h1>
        <h1><%= new Query().moveItMoveIt() %></h1>
    </body>
</html>
