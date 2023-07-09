<%-- 
    Document   : btnPayRoll
    Created on : Jul 9, 2023, 4:08:06 PM
    Author     : flami
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
             <input type="submit" name="action" value="ViewPayRoll">
        </form>
        <form action="MainController" method="POST">
             <input type="submit" name="action" value="Attendance">
        </form>
        <a href="CheckAttendance.jsp" >Check Attendance</a>
    </body>
</html>
