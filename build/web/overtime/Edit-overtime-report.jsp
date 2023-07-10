<%-- 
    Document   : Edit-overtime-report
    Created on : Jul 8, 2023, 9:46:45 PM
    Author     : Hào Cute
--%>


<%@page import="overtime.OverTimeReport_DTO"%>
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
            OverTimeReport_DTO reportIsEditing = (OverTimeReport_DTO) session.getAttribute("EDIT_OVERTIME_REPORT");
        %>
        <%
            if (reportIsEditing != null) {
        %>
        <form action="OverTimeController">
             <div class="user-details">
            <div class="input-box">
                <span class="details" value="<%= loginUser.getEmployeeName()%>">Employee:</span>
            </div><br>
            <div class="input-box">
                <span class="details">Date:</span>
                <input type="Date" name="dateOT" required="" value="<%=reportIsEditing.getDateOT()%>"></br>                  </div>
            <div class="input-box">
                <span class="details">Amount of Hours:</span>
                <input type="number" name="otHours" required="" min="1" value=""></br>

            </div>
            <div class="input-box">
                <label class="reason">Reason:</label>
                <textarea type="text" name="reason" value=""></textarea>
            </div>
            <input type="hidden" value="EditOverTime" name="action"></br>
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
                <input type="Date" name="dateOT" required="" value="" readonly=""></br>                  </div>
            <div class="input-box">
                <span class="details">Amount of Hours:</span>
                <input type="number" name="otHours" required="" min="1" value="" readonly=""></br>

            </div>
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
