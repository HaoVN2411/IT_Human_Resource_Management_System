<%-- 
    Document   : viewCandidate
    Created on : Jun 16, 2023, 1:27:26 PM
    Author     : flami
--%>

<%@page import="java.util.List"%>
<%@page import="Candidate.CandidateDTO"%>
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
            //            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            //            if (loginUser == null || !loginUser.getRoleID()) {
            //                response.sendRedirect("login.html");
            //                return;
            //            }
            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>

        <!--Logout-->
        <!--        <form action="MainController" method="POST">
                    <input type="submit" name="action" value="Logout"/>
                </form>-->

        <!--Search-->
        <form action="MainController">
            <input placeholder="Name of staff" type="text" name="search" value="<%= search%>"/>
            <input type="hidden" name="action" value="Search Candidate"/>
            <input  type="submit" value="Search"/> 
        </form>

        <!--Print List-->    
        <%
            List<CandidateDTO> listCandidate = (List<CandidateDTO>) request.getAttribute("LIST_CANDIDATE");
            if ((listCandidate == null || listCandidate.isEmpty()) && !search.equals("")) {
        %>
        <h3>No record!</h3>
        <%
            }
            if (listCandidate != null) {
                if (!listCandidate.isEmpty()) {
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
                    <th>Detail</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (CandidateDTO candidate : listCandidate) {
                %>       
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td><%= candidate.getId()%></td>
                    <td><%= candidate.getFullName()%></td>
                    <td><%= candidate.getGender()%></td>
                    <td><%= candidate.getDateOfBrith()%></td>
                    <td><%= candidate.getPhoneNumber()%></td>
                    <td><%= candidate.getAddress()%></td>
                    <td>
                        <input type="submit" value="Show Detail"/>                            
                        <input type="hidden" name="action" value="Show Candidate Detail"/>
                        <input type="hidden" name="candidateID" value="<%= candidate.getId()%>"/>
                    </td>
                </tr>                
            </form>

            <%
                }
            %>

        </tbody>
    </table>

    <%
            }
        }
    %>

    <Br></Br>
</body>

</html>