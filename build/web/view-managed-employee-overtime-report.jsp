<%-- 
    Document   : view-managed-employee-overtime-report
    Created on : Jul 8, 2023, 9:44:28 PM
    Author     : Hào Cute
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="OverTimeLog.OverTimeReport_DTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        //login nay de test chuc nang khong them vo
        <form action="LoginController">
            <input type="text" name="userID">
            <input type="password" name="password">
            <input type="submit">
        </form>
        <form action="OverTimeController">
            ID: <input type="text" name="searchOverTimeReportById" >
            Date: <input type="date" name="searchOverTimeReportByDate" >
            <input type="hidden" name="action" value="ViewManagedEmployeeOverTimeReport">
            <input type="submit" value="Search">
        </form>
        <%
            List<OverTimeReport_DTO> listReport = null;
            listReport = (List<OverTimeReport_DTO>) request.getAttribute("LIST_MANAGED_EMPLOYEE_OVERTIME_REPORT");
            if (listReport != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>EmployeeID</th>
                    <th>Date</th>
                    <th>Date Type</th>
                    <th>Date Name</th>
                    <th>Hours</th>
                    <th>Co-Salary</th>
                    <th>Reason</th>
                    <th>Status</th>
                    <th>Response</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    String status = null;
                    for (OverTimeReport_DTO report : listReport) {
                %>

            <form action="OverTimeController">
                <tr>
                    <td><%=count%></td>
                    <td><%=report.getEmployeeId()%></td>
                    <td><%=report.getDateOT()%></td>
                    <td><%=report.getDateType()%></td>
                    <td><%=report.getDateName()%></td>
                    <td><%=report.getOtHours()%></td>
                    <td><%=report.getCoSalary()%></td>
                    <td><%=report.getReason()%></td>
                    <%
                        if (report.isIsStatus() == null) {
                            status = "Waiting";
                    %>
                    <%
                        } else if (report.isIsStatus() == true) {
                            status = "Approved";
                        } else {
                            status = "Rejected";
                        }
                    %>
                    <td><%=status%></td>
                    <%
                        if (status.equals("Waiting")) {
                    %>
                <input type="hidden" name="overTimeID" value="<%=report.getOverTimeId()%>">
                <input type="hidden" name="employeeId" value="<%=report.getEmployeeId()%>">
                <td><input type="text" name="reasonReject" value=""></td>
                <input type="hidden" name="action" value="ConfirmOverTime">
                <td>
                    <input type="submit" name="confirm" value="Approve">
                    <input type="submit" name="confirm" value="Reject">
                </td>
                <%
                } else {
                %>
                <td><%=report.getReasonReject()%></td>
                <%
                    }
                %>
                </tr>
            </form>
            <%
                    count += 1;
                }
            %>

        </tbody>
    </table>
    <%
        }
    %>
    <%
        String message = (String) request.getParameter("MESSAGE");
        if (message != null) {
    %>
    <%=message%>
    <%
        }
    %>
</body>
</html>
