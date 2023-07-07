<%-- 
    Document   : HRS
    Created on : May 29, 2023, 11:42:41 AM
    Author     : Hào Cute
--%>

<%@page import="PayRoll.PayRoll_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="MainController" method="POST">
             <input type="submit" name="action" value="ViewPayRoll">
        </form>
        <a href="CheckAttendance.jsp" >Check Attendance</a>
        
        
    </body>
</html>
