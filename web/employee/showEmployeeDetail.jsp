<%-- 
    Document   : showEmployeeDetail
    Created on : Jul 5, 2023, 10:13:59 PM
    Author     : flami
--%>

<%@page import="employee.Employee_Info_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Detail</title>
          <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px; /* Added rounded border */
            background: white;
        }
        
        .employee-image {
            width: 200px;
            height: auto;
            margin-bottom: 20px;
            border-radius: 10px; /* Added rounded border */
        }
        
        .employee-info {
            font-size: 18px;
            margin-bottom: 10px;
        }
        
        .info-row {
            display: flex;
            align-items: center;
            justify-content: space-between;
            border-bottom: 1px solid #ccc;
            padding-bottom: 10px;
            margin-bottom: 10px;
        }
        
        .info-label {
            flex-basis: 150px;
            font-weight: bold;
        }
        
        .view-contract-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 20px;
            cursor: pointer;
        }
    </style>
    </head>
    <body>
        <%
            Employee_Info_DTO employeeInfo = (Employee_Info_DTO) request.getAttribute("EMPLOYEE_DETAIL");
            if (employeeInfo == null) {
                employeeInfo = new Employee_Info_DTO();
            }
        %>  
         <div class="container">
        <img class="employee-image" src="<%=employeeInfo.getImage()%>" alt="Employee Image">
        <div class="info-row">
            <span class="info-label">Employee ID:</span>
            <span class="employee-info"><%= employeeInfo.getEmployeeID()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Full Name:</span>
            <span class="employee-info"><%= employeeInfo.getFullName()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Gender:</span>
            <span class="employee-info"><%= employeeInfo.getGender()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Date of Birth:</span>
            <span class="employee-info"><%= employeeInfo.getDateOfBirth() %></span>
        </div>
        <div class="info-row">
            <span class="info-label">Phone Number:</span>
            <span class="employee-info"><%= employeeInfo.getPhoneNumber()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Email:</span>
            <span class="employee-info"><%= employeeInfo.getEmail()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Address:</span>
            <span class="employee-info"><%= employeeInfo.getAddress()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Human ID:</span>
            <span class="employee-info"><%= employeeInfo.getHumanId()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Nationality:</span>
            <span class="employee-info"><%= employeeInfo.getNationality()%></span>
        </div>
        <div class="info-row">
            <span class="info-label">Active Status:</span>
            <span class="employee-info"><%= employeeInfo.isIsActive() %></span>
        </div>
        
        <form action="EmployeeController" method="post">
            <input type="hidden" name="employeeID" value="<%=employeeInfo.getEmployeeID() %>">
            <input type="hidden" name="action" value="View Employee Contract Detail">
            <input class="view-contract-btn" type="submit" value="View Contract">
        </form>
    </div>
    </body>
</html>
