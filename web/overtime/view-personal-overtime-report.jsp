<%-- 
    Document   : view-personal-overtime-report
    Created on : Jul 8, 2023, 9:43:53 PM
    Author     : Hào Cute
--%>

<%@page import="overtime.OverTimeReport_DTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/view.css">

    </head>
    <body>

        <form action="OverTimeController">
            Date: <input type="date" name="searchOverTimeReportByDate" >
            <input type="hidden" name="action" value="ViewPersonOverTimeReport">
            <input type="submit" value="Search">
        </form>
        <%
            List<OverTimeReport_DTO> listReport = null;
            listReport = (List<OverTimeReport_DTO>) request.getAttribute("LIST_PERSON_OVERTIME_REPORT");
            if (listReport != null) {
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Date</th>
                    <th>Date Type</th>
                    <th>Date Name</th>
                    <th>Hours</th>
                    <th>Co-Salary</th>
                    <th>Reason</th>
                    <th>Status</th>
                    <th>Response</th>
                    <th>Edit</th>
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
                    <td><%=report.getReasonReject()%></td>
                    <%
                        if (status.equals("Waiting") || status.equals("Rejected")) {
                    %>
                <input type="hidden" name="overTimeID" value="<%=report.getOverTimeId()%>">
                <input type="hidden" name="dateOT" value="<%=report.getDateOT()%>">
                <input type="hidden" name="action" value="EditOverTimeButton">
                <td>
                    <input type="submit" name="confirm" value="Edit">
                </td>
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
