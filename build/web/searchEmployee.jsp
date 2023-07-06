<%-- 
    Document   : searchEmployee
    Created on : Jul 5, 2023, 3:36:56 PM
    Author     : flami
--%>

<%@page import="Employee_Info.Employee_Info_DTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
            String isActive = (String) request.getAttribute("STATUS_CONTRACT");
        %>
        <p>Status</p>
        <form action="MainController">
            <select name="statusActive" tabindex="1">
                <option value="All" <%= isActive != null
                        && isActive.equals("All")
                        ? "selected" : ""%>>All</option>
                <option value="Working" <%= isActive != null
                        && isActive.equals("Working")
                        ? "selected" : ""%>>Working</option>
                <option value="Not Working" <%= isActive != null
                        && isActive.equals("Not Working")
                        ? "selected" : ""%> >Not Working</option>
            </select>
            <input placeholder="ID or full name" type="text" name="search" value="<%= search%>"/>
            <input type="hidden" name="action" value="Search Employee"/>
            <input type="submit" value="Search"/>
        </form>

        <!--Print List-->    
        <%
            List<Employee_Info_DTO> listEmployee = (List<Employee_Info_DTO>) request.getAttribute("LIST_EMPLOYEE");
            if ((listEmployee == null || listEmployee.isEmpty()) && !search.equals("")) {
        %>
        <h2>No record!</h2>
        <%
            }
            if (listEmployee != null) {
                if (!listEmployee.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Full Name</th>
                    <th>Gender</th>
                    <th>Date Of Birth</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Role</th>
                    <th>Detail</th>
                    <th>Contract</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (Employee_Info_DTO employee : listEmployee) {
                %>       
                <tr>
                    <td><%= count++%></td>
                    <td><%= employee.getEmployeeID()%></td>
                    <td><%= employee.getFullName()%></td>
                    <td><%= employee.getGender()%></td>
                    <td><%= employee.getDateOfBirth()%></td>
                    <td><%= employee.getPhoneNumber()%></td>
                    <td><%= employee.getAddress()%></td>
                    <%
                        String role;
                        if (employee.getEmployeeID().substring(0, 2).equalsIgnoreCase("ss")) {
                            role = "Staff";
                        } else if (employee.getEmployeeID().substring(0, 2).equalsIgnoreCase("hs")) {
                            role = "HRS";
                        } else {
                            role = "HRM";
                        }
                    %>
                    <td><%= role%></td>
                    <td>
                        <form action="MainController">
                            <input type="hidden" name="search" value="<%=search%>">
                            <input type="hidden" name="statusActive" value="<%=isActive%>">
                            <input type="submit" value="Show Detail"/>                            
                            <input type="hidden" name="action" value="Show Employee Detail"/>
                            <input type="hidden" name="employeeID" value="<%= employee.getEmployeeID()%>"/>
                        </form>
                    </td>                    
                    <td>
                        <form action="MainController" method="post">
                            <input type="hidden" name="search" value="<%=search%>">
                            <input type="hidden" name="statusActive" value="<%=isActive%>">
                            <input type="hidden" name="employeeID" value="<%=employee.getEmployeeID()%>">
                            <input type="submit" value="View Contract">
                            <input type="hidden" name="action" value="View Employee Contract Detail"/>
                        </form>
                    </td>
                </tr>                


                <%
                    }
                %>

            </tbody>
        </table>

        <%
            String errorMessage = (String) request.getAttribute("ERROR_MESSAGE");
            if (errorMessage == null) {
                errorMessage = "";
            }
        %>
        <h2><%=errorMessage%></h2>
        <%
                }
            }
        %>

    </tbody>
</body>
</html>
