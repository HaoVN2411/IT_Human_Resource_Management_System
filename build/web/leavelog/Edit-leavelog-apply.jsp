<%-- 
    Document   : Edit-leavelog-apply
    Created on : Jul 9, 2023, 3:28:57 AM
    Author     : Hào Cute
--%>

<%@page import="leavelog.LeaveLog_DTO"%>
<%@page import="userlogin.User_Login_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/edit.css">

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
             <div class="user-details">
            <div class="input-box">
                <span class="details" value=" <%= loginUser.getEmployeeName()%>">Employee:</span>
            </div>
            <div class="input-box">
                <span class="details">Date:</span>
<input type="Date" name="dateLeave" required="" value="<%=reportIsEditing.getDateLeave()%>"></br>            <div class="input-box">
            </div>
            <div class="input-box">
                <label class="reason">Reason:</label>
                <textarea type="text" name="reason" value=""></textarea>
            </div>
            <input type="hidden" value="EditLeaveLog" name="action"></br>
            <button class="btn btn-edit" type="submit" value="Save">Save</button>
        </div>
    </form>
            
        <%
        } else {
        %>
        <form>
        <div class="user-details">
            <div class="input-box">
                <span class="details" value="" readonly="">Employee:</span>
            </div>
            <div class="input-box">
                <span class="details">Date:</span>
                <input type="date" name="dateOT" required="" value="" readonly=""></br>                  </div>
   
            <div class="input-box">
                <label class="reason" >Reason:</label>
                <textarea type="text" name="reason" value="" readonly=""></textarea>
            </div>
            <input type="hidden" value="EditOverTime" name="action" readonly=""></br>
                    <%            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>
                   <br>
            <button class="btn btn-edit" type="submit" value="Save" readonly="">Save</button>
        </div>
                </form>
        <%
            }
        %>


    </body>
</html>
