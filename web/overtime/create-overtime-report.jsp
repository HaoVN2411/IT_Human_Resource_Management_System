<%-- 
    Document   : create-overtime-report
    Created on : Jul 8, 2023, 9:43:12 PM
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
        <form action="OverTimeController">
            <div class="user-details">
                <div class="input-box">
                    <label class="details" vlaue="">Employee: <%= loginUser.getEmployeeName()%></label>
                </div>
                <div class="input-box">
                    <label class="details">Date:</label>
                    <input type="Date" name="dateOT" required="">
                </div>
                <div class="input-box">
                    <label class="details"> Amount of Hours:</label>
                    <input type="number" name="otHours" required="" min="1">                            </select>
                </div>
                <div class="input-box">
                    <label class="reason">Reason:</label>
                    <textarea type="text" name="reason"></textarea>
                </div>
                <div>
                    <div class="box-footer text-center">
                        <input type="hidden" value="CreateOverTimeReport" name="action"> <br>
                                <%            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <%= message%>
                        <button type="submit" class="btn btn-success">Create</button>
                    </div>

                </div>
            </div>
        </form>



    </body>
</html>