<%-- 
    Document   : CheckAttendance
    Created on : Jul 5, 2023, 3:25:30 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="CheckIn">
        </form>
        ${requestScope.MESSAGE}
    </body>
</html>
