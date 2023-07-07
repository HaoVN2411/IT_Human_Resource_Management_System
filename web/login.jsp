<%-- 
    Document   : login
    Created on : May 29, 2023, 11:39:29 AM
    Author     : Hào Cute
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="app.css">
    </head>
    <body>
        <div class="login-page">
            <div class="form">
                <p>WelCome</p>
                <form class="login-form" action="MainController" method="GET">
                    <input type="text" placeholder="username" name="userID" required=""/>
                    <input type="password" placeholder="password" name="password" required=""/>
                    <%
                        String error = (String) request.getAttribute("ERROR_MESSAGE");
                        if(error == null) error = "";
                    %>
                    <%=error %>
                    <a href="forgotPassword.jsp" >ForgotPassword</a>
                    <button><input type="hidden" name="action" value="Login">login</button>
                    <p class="message">Not registered? <a href="#">Create an account</a></p>
                </form>
            </div>
        </div> 
    </body>
</html>
