
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*, java.sql.*"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*"%>
<%!
    private static class ModifyRecords {
        public String moveItMoveIt(){

            //------------------------------------------------------------------------
            // Establish Database Connection
            //------------------------------------------------------------------------

            String connectionURL = "jdbc:mysql://127.0.0.1:3306/coms363_proj1b";
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
            // Modify Records
            //------------------------------------------------------------------------

            try{
                Statement stmt  = connection.createStatement();

                String change = "UPDATE students SET name = \"Scott\" WHERE ssn = \"746897816\";";
                stmt.executeUpdate(change);

            }
            catch (SQLException e) {
                e.printStackTrace();
                return "Error modifying records: \n"+ Arrays.toString(e.getStackTrace());
            }


            return "Your data record has successfully been modified.";
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Modify Records Result</title>
    </head>
    <body>
        <h1><%= new ModifyRecords().moveItMoveIt() %></h1>
    </body>
</html>
