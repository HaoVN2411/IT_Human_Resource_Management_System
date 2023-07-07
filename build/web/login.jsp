<%-- 
    Document   : login
    Created on : May 29, 2023, 11:39:29 AM
    Author     : Hào Cute
--%>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Animated Login Form</title>
        <link rel="stylesheet" type="text/css" href="CSS/login.css">
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>
        <!--        <img class="wave" src="../image/wave.svg">-->
        <div class="container">
            <div class="img">
                <img src="imageLogin/1.svg">		</div>
            <div class="login-content">

                <form action="MainController" method="post">
                    <img src="imageLogin/avatar.svg">
                    <h2 class="title">Welcome</h2>
                    <div class="input-div one">
                        <div class="i">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="div">
                            <!--                            <h5>Username</h5>-->
                            <input placeholder="Username" type="text" class="input" name="userID">
                        </div>
                    </div>
                    <div class="input-div pass">
                        <div class="i"> 
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="div">
                            <!--                            <h5>Password</h5>-->
                            <input placeholder="Password" type="password" class="input" name="password">
                        </div>
                    </div>
                    <a href="#">Forgot Password?</a>
                    <input type="submit" class="btn" name="action" value="Login">
                </form>

            </div>
        </div>
        <script type="text/javascript" src="login.js"></script>
    </body>
</html>

