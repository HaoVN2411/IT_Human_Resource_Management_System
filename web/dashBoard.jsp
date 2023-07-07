<%-- 
    Document   : dashboard
    Created on : Jul 7, 2023, 2:49:56 PM
    Author     : flami
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Thông tin</title>
</head>
<body>
    <h1>Thông tin</h1>

    <%-- Dữ liệu bảng thông tin (có thể thay đổi) --%>
    <% 
        List<String[]> infoTable = new ArrayList<>();
        infoTable.add(new String[]{"001", "John Doe", "john.doe@example.com"});
        infoTable.add(new String[]{"002", "Jane Smith", "jane.smith@example.com"});
        infoTable.add(new String[]{"003", "David Johnson", "david.johnson@example.com"});
    %>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <%-- Duyệt qua các hàng trong bảng thông tin và hiển thị --%>
        <% for (String[] row : infoTable) { %>
            <tr>
                <%-- Hiển thị các giá trị trong từng cột --%>
                <% for (String value : row) { %>
                    <td><%= value %></td>
                <% } %>
            </tr>
        <% } %>
    </table>
</body>
</html>