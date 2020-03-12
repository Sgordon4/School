
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*, java.util.*, java.sql.*"%>
<%@ page import="javax.servlet.http.*, javax.servlet.*"%>
<%!
    private static class Query {
        public ArrayList<String> moveItMoveIt(){

            ArrayList<String> results = new ArrayList<String>();

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
                results.add("Error creating database connection: \n"+ Arrays.toString(e.getStackTrace()));
                return results;
            }

            //------------------------------------------------------------------------
            // Query Tables
            //------------------------------------------------------------------------

            try{
                Statement stmt  = connection.createStatement();
                ResultSet rs;

                String query = "SELECT number, name FROM courses WHERE department_code = (SELECT code FROM departments WHERE name = 'Computer Science') OR department_code = (SELECT code FROM departments WHERE name = 'Landscape Architect');";
                rs = stmt.executeQuery(query);

                while(rs.next()){
                    results.add("Number: "+rs.getString("number")+", Name: "+rs.getString("name"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                results.add("Error querying tables: \n"+ Arrays.toString(e.getStackTrace()));
                return results;
            }

            return results;
        }
    }
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=MacRoman">
        <title>Query Result 3</title>
    </head>
    <body>
        <h1>This is the result of query 3.</h1>
        <h1>
            <%
                ArrayList<String> results = new Query().moveItMoveIt();

                for (Iterator iter = results.iterator(); iter.hasNext();) {
                    out.println("<tr><td>" + (String)(iter.next()) + "</td></tr><br>");
                }
            %>
        </h1>
    </body>
</html>
