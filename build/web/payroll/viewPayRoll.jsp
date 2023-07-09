<%-- 
    Document   : PayRoll
    Created on : Jun 20, 2023, 1:03:16 AM
    Author     : CAO-KIEN-QUOC
--%>
<%@page import="payroll.PayRoll_DTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <%
            PayRoll_DTO payRoll = (PayRoll_DTO) request.getAttribute("PAYROLL");

            if (payRoll != null) {
                // Biến payRoll đã nhận giá trị từ thuộc tính session "PAYROLL"
%>
        Full Name: <%= payRoll.getFullName()%><br/>
        of_Hours: <%= payRoll.getOfficeHours()%><br/>
        ot_Hours: <%= payRoll.getOtHours()%><br/>
        ot_Income: <%= payRoll.getOt_income()%><br/>
        standard_income: <%= payRoll.getStand_income()%><br/>
        standard_income: <%= payRoll.getPaidDate()%><br/>
        total: <%= payRoll.getTotal()%><br/>
        BHXH: <%= payRoll.getBHXH()%><br/>
        BHTN: <%= payRoll.getBHTN()%><br/>
        TNCN: <%= payRoll.getTNCN()%><br/>
        allowance: <%= payRoll.getAllowance()%><br/>
        <%
        } else {
            // Biến payRoll chưa nhận giá trị từ thuộc tính session "PAYROLL"
        %>
        No PayRoll information found.
        <%
            }
        %>
    </body> 
</html>
