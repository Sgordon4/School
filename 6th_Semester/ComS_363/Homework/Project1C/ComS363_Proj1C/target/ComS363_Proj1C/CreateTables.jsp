<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP Page</title>
</head>
<body>
    <h1>Hello World!</h1>

    <%
        System.out.println("Hayyeet");
        System.out.println("Ur boi, we did it");
    %>
    <button type="button" onclick="alert('You pressed the button!')">Click me!</button><br>
    <form action = "JavaExample.jsp">
        <input type="submit" value="open other jsp">
    </form>

</body>
</html>
