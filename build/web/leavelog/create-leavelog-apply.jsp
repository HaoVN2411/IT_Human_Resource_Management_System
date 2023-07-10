<%-- 
    Document   : create-leavelog-apply
    Created on : Jul 9, 2023, 2:37:46 AM
    Author     : Hào Cute
--%>


<%@page import="userlogin.User_Login_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>       
        <link rel="stylesheet" type="text/css" href="css/create.css">
        
    </head>
    <body>
        
        <%
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (loginUser == null) {
                loginUser = new User_Login_DTO();
            }
        %>
       <form action="LeaveLogController">
            <div class="user-details">
                <div class="input-box">
                    <label class="details" vlaue="">Employee: <%= loginUser.getEmployeeName()%></label>
                </div><br>
                <div class="input-box">
                    <label class="details">Date:</label>
                    <input type="date" name="dateLeave" required="">
                </div>
                <div class="input-box">
                    <label class="reason">Reason:</label>
                    <textarea type="text" name="reason" value="CreateLeaveLogApplication"></textarea>
                </div>
                <div>
                    <div class="box-footer text-center">
                        <input type="hidden" value="CreateLeaveLogApplication" name="action"> <br>
                                <%            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>
                        <button type="submit" class="btn btn-success" value="Apply">Create</button>
                    </div>

                </div>
            </div>
        </form>
    </body>
</html>
