<%-- 
    Document   : showInformationEmployee
    Created on : Jul 2, 2023, 3:57:11 PM
    Author     : flami
--%>


<%@page import="Employee_Info.Employee_Info_DTO"%>

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
            <input type="hidden" name="employeeID" value="<%=employeeInfo.getEmployeeID() %>">
            <input type="hidden" name="action" value="View Employee Contract Detail">
            <input type="submit" value="View Contract">
        </form>
