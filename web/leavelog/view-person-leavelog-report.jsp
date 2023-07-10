<%-- 
    Document   : view-person-leavelog-report
    Created on : Jul 9, 2023, 3:19:27 AM
    Author     : Hào Cute
--%>


<%@page import="leavelog.LeaveLog_DTO"%>
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

        <form action="LeaveLogController">
            Date: <input type="date" name="searchLeaveLogApplyByDate" >
            <input type="hidden" name="action" value="ViewPersonLeaveLogApplication">
            <input type="submit" value="Search">
        </form>
        <%
            List<LeaveLog_DTO> listReport = null;
            listReport = (List<LeaveLog_DTO>) request.getAttribute("LIST_PERSON_LEAVELOG_APPLY");
            if (listReport != null) {
        %>

        <table border="1">
            <thead>
                <tr>
                    <th>NO</th>
                    <th>Date</th>
                    <th>Reason</th>
                    <th>Status</th>

                    <th></th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    String status = null;
                    for (LeaveLog_DTO report : listReport) {
                %>

            <form action="LeaveLogController">
                <tr>
                    <td><%=count%></td>
                    <td><%=report.getDateLeave()%></td>
                    <td><%=report.getReason()%></td>
                    <%
                        if (report.getIsStatus()== null) {
                            status = "Waiting";
                    %>
                    <%
                        } else if (report.getIsStatus() == true) {
                            status = "Approved";
                        } else {
                            status = "Rejected";
                        }
                    %>
                    <td><%=status%></td>
                    <%
                        if (status.equals("Waiting") || status.equals("Rejected")) {
                    %>
                <input type="hidden" name="leaveLogID" value="<%=report.getLeaveLogID()%>">
                <input type="hidden" name="dateLeave" value="<%=report.getDateLeave()%>">
                <input type="hidden" name="action" value="EditLeaveLogButton">
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
