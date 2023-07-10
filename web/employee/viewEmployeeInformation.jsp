<%-- 
    Document   : showInformationEmployee
    Created on : Jul 2, 2023, 3:57:11 PM
    Author     : flami
--%>


<%@page import="employee.Employee_Info_DTO"%>
<!DOCTYPE html>
<html>
<head>
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
<body>  <%
            Employee_Info_DTO employeeInfo = (Employee_Info_DTO) request.getAttribute("USER_INFO");
            if(employeeInfo==null){
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
        <div class="info-row">
            <span class="info-label">Contract ID:</span>
            <span class="employee-info"><%= employeeInfo.getContractID()%></span>
        </div>
        
        <form action="EmployeeController" method="post">
            <input type="hidden" name="employeeID" value="<%=employeeInfo.getEmployeeID() %>">
            <input type="hidden" name="action" value="View Employee Contract Detail">
            <input class="view-contract-btn" type="submit" value="View Contract">
        </form>
    </div>
</body>
</html>