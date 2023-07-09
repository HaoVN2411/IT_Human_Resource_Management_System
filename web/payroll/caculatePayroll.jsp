<%-- 
    Document   : HRM
    Created on : May 29, 2023, 11:43:26 AM
    Author     : Hào Cute
--%>

<%@page import="payroll.PayRoll_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="PayRollController" method="POST">
             <input type="submit" name="action" value="PayRoll">
        </form>
    </body>
</html>
