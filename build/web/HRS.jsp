<%-- 
    Document   : newjsp
    Created on : Jun 30, 2023, 1:04:39 PM
    Author     : Hào Cute
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CreateLeaveLogController">
            <input type="text" name="reason">
            <input type="date" name="dateLeave">
            <input type="submit">
        </form>

        <form action="EditButtonController">\
            <input type="text" name='overTimeId'>
            <input type="text" name="reason">
            <input type="date" name="dateOT">
            <input type="number" name="otHours">
            <input type="submit">

        </form>

        <form action="LoginController">
            <input type="text" name="userID">
            <input type="password" name="password">
            <input type="submit">
        </form>

        <form action="CreateLeaveLogController">
            <input type="date" name="dateLeave">
            <input type="text" name="reason">
            <input type="submit">
        </form>

        <form action="ViewPersonLeaveLogApplicationController">
            <input type="date" name="searchLeaveLogApplyByDate">
            <input type="submit">
        </form>

        <form action="ConfirmLeaveLogController">
            <input type="date" name="dateLeave">
            <input type="text" name="employeeId">
            <input type="submit" name="confirm" value="Approved">
            <input type="submit" name="confirm" value="Rejected">
        </form>

        <form action="ViewManagedEmployeeLeaveLogApplicationController">
            <input type="text" name="searchLeaveLogApplyById">
            <input type="date" name="searchLeaveLogApplyByDate">
            <input type="submit" >

        </form>

        <p>Button</p>    
        <form action="EditLeaveLogButtonController">
            <input type="text" name="searchLeaveLogApplyById">
            <input type="date" name="searchLeaveLogApplyByDate">
            <input type="submit" >

        </form>

        <p>Edit</p>

        <form action="EditLeaveLogButtonController">
            <input type="text" name="leaveLogID">
            <input type="text" name="reason">
            <input type="date" name="dateLeave">
            <input type="submit" >
        </form>

        <form action="EditLeaveLogController">
            <input type="text" name="reason">
            <input type="date" name="dateLeave">
            <input type="submit" >
        </form>
    </body>
</html>
