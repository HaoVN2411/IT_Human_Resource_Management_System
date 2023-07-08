<%-- 
    Document   : Edit-overtime-report
    Created on : Jul 8, 2023, 9:46:45 PM
    Author     : Hào Cute
--%>

<%@page import="OverTimeLog.OverTimeReport_DTO"%>
<%@page import="User_Login_Controller.User_Login_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (loginUser == null) {
                loginUser = new User_Login_DTO();
            }
            OverTimeReport_DTO reportIsEditing = (OverTimeReport_DTO) session.getAttribute("EDIT_OVERTIME_REPORT");
        %>
        <%
            if (reportIsEditing != null) {
        %>
        <form action="OverTimeController">
            Employee: <%= loginUser.getEmployeeName()%></br>
            Date: <input type="Date" name="dateOT" required="" value="<%=reportIsEditing.getDateOT()%>"></br>
            Reason: <input type="text" name="reason" value=""></br>
            Amount of Hours: <input type="number" name="otHours" required="" min="1" value=""></br>
            <input type="hidden" value="EditOverTime" name="action"></br>
            <input type="submit" value="Save">
        </form>
        <%
        } else {
        %>
        Employee: <%= loginUser.getEmployeeName()%> </br>
        Date: <input type="text" readonly=""> </br>
        Reason: <input type="text" readonly=""> </br>
        Amount of Hours: <input type="text" readonly=""> </br>
        <input type="submit" value="Save">
        <%
            }
        %>

        <%            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>
    </body>
</html>
