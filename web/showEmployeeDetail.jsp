<%-- 
    Document   : showEmployeeDetail
    Created on : Jul 5, 2023, 10:13:59 PM
    Author     : flami
--%>

<%@page import="Employee_Info.Employee_Info_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Detail</title>
    </head>
    <body>
        <%
            Employee_Info_DTO employeeInfo = (Employee_Info_DTO) request.getAttribute("EMPLOYEE_DETAIL");
            if (employeeInfo == null) {
                employeeInfo = new Employee_Info_DTO();
            }
        %>  
        <img src="<%=employeeInfo.getImage()%>" alt="Employee Image" style="width: 500px; height: auto;"><br/>
        <%= employeeInfo.getEmployeeID()%><br/>
        <%= employeeInfo.getFullName()%><br/>
        <%= employeeInfo.getGender()%><br/>
        <%= employeeInfo.getDateOfBirth()%><br/>
        <%= employeeInfo.getPhoneNumber()%><br/>
        <%= employeeInfo.getEmail()%><br/>
        <%= employeeInfo.getAddress()%><br/>
        <%= employeeInfo.getHumanId()%><br/>
        <%= employeeInfo.getNationality()%><br/>
        <%= employeeInfo.isIsActive()%>
    </body>
</html>
