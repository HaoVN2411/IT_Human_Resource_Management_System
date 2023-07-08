<%-- 
    Document   : Edit-leavelog-apply
    Created on : Jul 9, 2023, 3:28:57 AM
    Author     : Hào Cute
--%>

<%@page import="LeaveLog.LeaveLog_DTO"%>
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
            LeaveLog_DTO reportIsEditing = (LeaveLog_DTO) session.getAttribute("EDIT_LEAVELOG_APPLY");
        %>
        <%
            if (reportIsEditing != null) {
        %>
        <form action="LeaveLogController">
            Employee: <%= loginUser.getEmployeeName()%></br>
            Date: <input type="Date" name="dateLeave" required="" value="<%=reportIsEditing.getDateLeave()%>"></br>
            Reason: <input type="text" name="reason" value=""></br>
            <input type="hidden" value="EditLeaveLog" name="action"></br>
            <input type="submit" value="Save">
        </form>
        <%
        } else {
        %>
        Employee: <%= loginUser.getEmployeeName()%> </br>
        Date: <input type="text" readonly=""> </br>
        Reason: <input type="text" readonly=""> </br>
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
