<%-- 
    Document   : showDetaiCandidate
    Created on : Jun 16, 2023, 3:01:15 AM
    Author     : flami
--%>

<%@page import="Contract.TemporaryContractDTO"%>
<%@page import="Contract.ContractError"%>
<%@page import="java.time.LocalDate"%>
<%@page import="Candidate.CandidateDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CANDIATE DETAIL</title>
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
        <%
            ContractError contractErr = new ContractError();

            if (tempContract == null) {
                tempContract = new TemporaryContractDTO();
            }
        %>

        <!--Update Form-->  
        <%
            if(status.equals("REJECTED") || status.equals("None")){
        %>
        <div>
            <form action="updateCandidate.jsp" method="POST">
                <%
                    session.setAttribute("CANDIDATE", candidateDetail);
                %>
                <input type="hidden" name="candidateID" value="<%=candidateDetail.getId()%>">
                <input name="action" type="submit"  value="Update" tabindex="2">
            </form>            
        </div>


        <!--Contract Form-->  
        <%
            }
            String statusContract = (String) request.getAttribute("STATUS_CONTRACT");
            if (statusContract != null) {
        %>
        <p><%=statusContract%></p>
        <%
        } else {
            ContractError conErr = (ContractError) request.getAttribute("ERROR_CONTRACT");
            if (conErr == null) {
                conErr = new ContractError();
            }

            if (!status.equals("None")) {
        %>
        <p>Contract of this candidate is created</p>
        <%
            } else {
        %>
        <form action="MainController" method="POST">
            <fieldset>
                <label for="date">Start Date</label><br/>
                <input type="date" name="startDate" min="<%=LocalDate.now().toString()%>"
                       value="<%=tempContract.getStartDate()%>" tabindex="1">
            </fieldset>
            <fieldset>
                <label for="date">Salary</label><br/>
                <input type="text" name="salary" 
                       <%
                           String salary;
                           if (tempContract.getSalary() == 0)
                               salary = "";
                           else
                               salary = String.valueOf(tempContract.getSalary());
                       %>
                       value="<%=salary%>" tabindex="2" required>
                <%=conErr.getSalaryError()%>   
            </fieldset>
            <fieldset>
                <label for="description">Notation</label><br/>
                <input placeholder="Type your Message Here...." name="description"
                       <%
                           if (tempContract.getDescription() == null)
                               tempContract.setDescription("");
                       %>
                       value="<%=tempContract.getDescription()%>" type="text"
                       tabindex="3">
                <div class="errorMessage">  
                    <%=conErr.getNotationError()%>                       
                </div>                     
            </fieldset>
            <fieldset>
                <input type="hidden" name="candidateID" value="<%=candidateDetail.getId()%>">
                <input type="reset" tabindex="4">
                <input name="action" type="submit"  value="Create Contract" tabindex="5">
                <div class="errorMessage">  
                    <%=conErr.getOtherError()%>                       
                </div>   
            </fieldset>
        </form>
        <%
                    }
                }
               // session.removeAttribute("CANDIDATE");
            }
        %>
    </body>
</html>
