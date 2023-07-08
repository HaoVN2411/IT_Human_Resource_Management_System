<%-- 
    Document   : create-overtime-report
    Created on : Jul 8, 2023, 9:43:12 PM
    Author     : Hào Cute
--%>

<%@page import="User_Login_Controller.User_Login_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        //login nay de test chuc nang khong them vo
        <form action="LoginController">
            <input type="text" name="userID">
            <input type="password" name="password">
            <input type="submit">
        </form>
        <%
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (loginUser == null){
                loginUser = new User_Login_DTO();
            }
        %>
        <form action="OverTimeController">
            Employee: <%= loginUser.getEmployeeName()%>
            Date: <input type="Date" name="dateOT" required="">
            Reason: <input type="text" name="reason">
            Amount of Hours: <input type="number" name="otHours" required="" min="1">
            <input type="hidden" value="CreateOverTimeReport" name="action">
            <input type="submit" value="Create">
        </form>

        <%            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>

    </body>
</html>
