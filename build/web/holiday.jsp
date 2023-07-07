<%-- 
    Document   : holiday
    Created on : Jul 7, 2023, 2:33:20 AM
    Author     : flami
--%>

<%@page import="holiday.holidayDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Holiday Page</title>
    </head>
    <body>
        <%
            holidayDTO holiday = (holidayDTO) request.getAttribute("holiday");
            
        %>
        <form>
            <input type="date" id="birthdate" name="dateOfBirth" 
                   value="<%= holiday.getDate()!= null
                                   ? holiday.getDate().toString() : ""%>"
                   min="1963-01-01" max="2005-01-01" tabindex="3" required>
            <input placeholder="Write notation here!" type="text" name="note" value="<%=holiday.getNote() %>">
            <input type="submit" name="action" value="Add">
            <input type="submit" name="action" value="Add">
            <input type="submit" name="action" value="Add">
        </form>

    </body>
</html>
