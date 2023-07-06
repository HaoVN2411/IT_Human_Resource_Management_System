<%-- 
    Document   : showInformationEmployee
    Created on : Jul 2, 2023, 3:57:11 PM
    Author     : flami
--%>


<%@page import="Employee_Info.Employee_Info_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Inforamtion</title>
    </head>
    <body>
        <%
            Employee_Info_DTO employeeInfo = (Employee_Info_DTO) request.getAttribute("USER_INFO");
            if(employeeInfo==null){
                employeeInfo = new Employee_Info_DTO();
            }
        %>  
        <img src="<%=employeeInfo.getImage()%>" alt="Employee Image" style="width: 500px; height: auto;"><br/>
        <%= employeeInfo.getEmployeeID()%><br/>
        <%= employeeInfo.getFullName()%><br/>
        <%= employeeInfo.getGender()%><br/>
        <%= employeeInfo.getDateOfBirth() %><br/>
        <%= employeeInfo.getPhoneNumber()%><br/>
        <%= employeeInfo.getEmail()%><br/>
        <%= employeeInfo.getAddress()%><br/>
        <%= employeeInfo.getHumanId()%><br/>
        <%= employeeInfo.getNationality()%><br/>
        <%= employeeInfo.isIsActive() %>
        <%= employeeInfo.getContractID()%>
        
        <form action="MainController" method="post">
            <input type="hidden" name="contractID" value="<%=employeeInfo.getContractID() %>">
            <input type="submit" name="action" value="View Contract">
        </form>
    </body>
</html>
