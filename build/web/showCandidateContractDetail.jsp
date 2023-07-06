<%-- 
    Document   : rejectForm
    Created on : Jun 22, 2023, 9:35:25 PM
    Author     : flami
--%>

<%@page import="User_Login_Controller.User_Login_DTO"%>
<%@page import="Contract.TemporaryContractDTO"%>
<%@page import="Candidate.CandidateDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            CandidateDTO candidateDetail = (CandidateDTO) request.getAttribute("CANDIDATE");
            if (candidateDetail != null) {
                TemporaryContractDTO tempContract = (TemporaryContractDTO) request.getAttribute("TEMPORARY_CONTRACT");
                String status;
                if (tempContract == null)
                    status = "None";
                else
                    status = tempContract.getStatus();
        %>  
        <img src="<%=candidateDetail.getImage()%>" alt="Ảnh C1114" style="width: 500px; height: auto;"><br/>
        <%= candidateDetail.getId()%><br/>
        <%= candidateDetail.getFullName()%><br/>
        <%= candidateDetail.getGender()%><br/>
        <%= candidateDetail.getDateOfBrith()%><br/>
        <%= candidateDetail.getPhoneNumber()%><br/>
        <%= candidateDetail.getEmail()%><br/>
        <%= candidateDetail.getAddress()%><br/>
        <%= candidateDetail.getHumanId()%><br/>
        <%= candidateDetail.getNationality()%><br/>
        <%= candidateDetail.getNotation()%><br/>
        <%= candidateDetail.getEmail()%>
        <p>Status Contract: <%=status%></p>
        <%=tempContract.getContractID()%><br/>
        <%=tempContract.getCreatorID()%><br/>
        <%=tempContract.getSalary()%><br/>
        <%=tempContract.getAllowance()%><br/>
        <%=tempContract.getStartDate()%><br/>
        <%=tempContract.getDescription()%><br/>
        <%=tempContract.getReason()%><br/>
        <%
            String roleName;
            HttpSession Session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) Session.getAttribute("USER_LOGIN");
            roleName = userLogin.getRoleName();
            if (roleName.equals("HRM")) {
        %>
        <form action="MainController">
            <input type="hidden" name="candidateID" value="<%= candidateDetail.getId()%>"/>
            <input type="hidden" name="contractID" value="<%= tempContract.getContractID()%>"/>
            <input type="submit" name="action" value="Print Contract"/>
            <input type="submit" name="action" value="Approve Contract"/>
            <input type="submit" name="action" value="Reject Contract"/>
            <div><input placeholder="Description.." type="text" name="description"></div>            
        </form>
        <%
        } else if (roleName.equals("HRS")) {
        %>
        <form action="MainController" >
            <input type="hidden" name="candidateID" value="<%= candidateDetail.getId()%>"/>
            <input type="hidden" name="contractID" value="<%= tempContract.getContractID()%>"/>
            <input type="submit" name="action" value="Print Contract"><br>
            <!--<input type="submit" name="action" value="Delete Contract"/><br>-->       
        </form>   

        <form action="MainController" method="post" enctype="multipart/form-data">
            <input type="hidden" name="candidateID" value="<%= candidateDetail.getId()%>"/>
            <input type="hidden" name="contractID" value="<%= tempContract.getContractID()%>"/>
            <input type="file" name="fileContract" value="Browse" multiple>
            <input type="submit" name="action" value="Apply Contract"><br>
            <!--<input type="submit" name="action" value="Delete Contract"/><br>-->       
        </form>        
        <%
            if (tempContract.getStatus().equals("REJECTED")) {
        %>
        <form action="updateContract.jsp">
            <%
                session.setAttribute("CANDIDATE", candidateDetail);
                session.setAttribute("TEMPORARY_CONTRACT", tempContract);
            %>
            <input type="submit" name="action" value="Update Contract"><br>          
        </form>
        <%
                    }
                }
            }
            String errMessage = (String) request.getAttribute("ERROR_MESSAGE");
            if (errMessage == null) {
                errMessage = "";
            }
        %>
        <h4><%=errMessage%></h4>
    </body>
</html>
