<%-- 
    Document   : create-leavelog-apply
    Created on : Jul 9, 2023, 2:37:46 AM
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
            if (loginUser == null) {
                loginUser = new User_Login_DTO();
            }
        %>
        <form action="LeaveLogController">
            Employee: <%= loginUser.getEmployeeName()%>
            Date: <input type="Date" name="dateLeave" required="">
            Reason: <input type="text" name="reason">
            <input type="hidden" value="CreateLeaveLogApplication" name="action">
            <input type="submit" value="Apply">
        </form>

        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>
    </body>
</html>
