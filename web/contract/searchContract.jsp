<%-- 
    Document   : viewCandidate
    Created on : Jun 16, 2023, 1:27:26 PM
    Author     : flami
--%>

<%@page import="contract.TemporaryContractDTO"%>
<%@page import="contract.CandidateContractDTO"%>
<%@page import="contract.CandidateContractDTO"%>
<%@page import="java.util.List"%>
<%@page import="candidate.CandidateDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Responesive Admin Dashboard | Redesign</title>

    </head>

    <body>

        <%
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
            String statusContract = (String) request.getAttribute("STATUS_CONTRACT");
        %>

        <!--Logout-->
        <!--        <form action="MainController" method="POST">
                    <input type="submit" name="action" value="Logout"/>
                </form>-->

        <!--Search-->
        <form action="ContractController">
            <select name="statusContract" class="gender" tabindex="1">
                    <option value="All" <%= statusContract != null
                            && statusContract.equals("All")
                            ? "selected" : ""%>>All</option>
                <option value="Rejected" <%= statusContract != null
                        && statusContract.equals("Rejected")
                        ? "selected" : ""%>>Rejected</option>
                <option value="Approved" <%= statusContract != null
                        && statusContract.equals("Approved")
                        ? "selected" : ""%> >Approved</option>
                <option value="Processing"  <%= statusContract != null
                        && statusContract.equals("Processing")
                        ? "selected" : ""%>>Processing</option>
            </select><br/>
            <input type="text" name="search" value="<%= search%>"/>
            <input type="hidden" name="action" value="Search Contract"/>
            <input type="submit" value="Search"/>
        </form>

        <!--Print List-->    
        <%
            List<CandidateContractDTO> listCandidateContract = (List<CandidateContractDTO>) request.getAttribute("LIST_CANDIDATE_CONTRACT");
            if ((listCandidateContract == null || listCandidateContract.isEmpty()) && !search.equals("")) {
        %>
        <h2>No record!</h2>
        <%
            }
            if (listCandidateContract != null) {
                if (!listCandidateContract.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID Contract</th>
                    <th>Full Name</th>
                    <th>Start Date</th>
                    <th>Salary</th>
                    <th>Status</th>
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (CandidateContractDTO candidateContract : listCandidateContract) {
                        CandidateDTO candidate = candidateContract.getCandidate();
                        TemporaryContractDTO tempContract = candidateContract.getTempCont();
                %>       
            <form action="ContractController" method="POST">
                <tr>
                    <td><%= count++%></td>
                    <td><%= tempContract.getContractID()%></td>
                    <td><%= candidate.getFullName()%></td>
                    <td><%= String.valueOf(tempContract.getStartDate())%></td>
                    <td><%= tempContract.getSalary()%></td>
                    <td><%= tempContract.getStatus()%></td>                        
                    <td>
                        <input type="hidden" name="action" value="Show Detail Temporary Contract"/>
                        <input type="hidden" name="candidateID" value="<%= candidate.getId()%>"/>
                        <input type="hidden" name="contractID" value="<%= tempContract.getContractID()%>"/>
                        <input type="hidden" name="statusContract" value="<%=statusContract%>"/>
                        <input type="submit" value="Show Detail"/>
                    </td>
                </tr>                
            </form>

            <%
                        }
                    }
                }
            %>

        </tbody>
    </table>

<body/>           
</html>