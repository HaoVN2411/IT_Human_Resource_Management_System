<%-- 
    Document   : updateCandidate
    Created on : Jun 21, 2023, 11:11:57 AM
    Author     : flami
--%>

<%@page import="Candidate.Candidate"%>
<%@page import="Candidate.CandidateError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CANDIDATE FORM</title>
        <link rel="stylesheet" href="CSS/candidate.css"/>
    </head>
    <body>
        <%
            CandidateError candidateError = (CandidateError) request.getAttribute("CANDIDATE_ERROR");
            if (candidateError == null) {
                candidateError = new CandidateError();
            }

            Candidate candidate = (Candidate) session.getAttribute("CANDIDATE");
            if (candidate == null) {
                candidate = (Candidate) request.getAttribute("CANDIDATE");
                candidate = new Candidate();
            }
        %>
        <div class="container">  
            <form id="contact" action="MainController" method="post" enctype="multipart/form-data">
                <h3 style="text-align: center;">CANDIDATE</h3>
                <h4>Contact us today, and get reply with in 24 hours!</h4>

                <fieldset>
                    <label for="text">Full Name</label>
                    <input placeholder="Jony Dang" type="text" name="fullName" 
                           value="<%=candidate.getFullName()%>" tabindex="1" 
                           required autofocus>
                    <div class="errorMessage">  
                        <%= candidateError.getFullNameError()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="gender">Gender</label><br/>
                    <select name="gender" class="gender" tabindex="2" value="<%=candidate.getGender()%>">
                        <option value="male" <%= candidate.getGender() != null
                                && candidate.getGender().equals("male")
                                ? "selected" : ""%>>Male</option>
                        <option value="female" <%= candidate.getGender() != null
                                && candidate.getGender().equals("female")
                                ? "selected" : ""%>>Female</option>
                        <option value="other" <%= candidate.getGender() != null
                                && candidate.getGender().equals("other")
                                ? "selected" : ""%>>Other</option>
                    </select><br/>
                </fieldset>
                <fieldset>
                    <label for="date">Date Of Birth</label><br/>
                    <input type="date" id="birthdate" name="dateOfBirth" 
                           value="<%= candidate.getDateOfBrith() != null
                                   ? candidate.getDateOfBrith().toString() : ""%>"
                           min="1963-01-01" max="2005-01-01" tabindex="3" required>
                </fieldset>
                <fieldset>
                    <label for="phoneNumber">Phone Number</label>
                    <input placeholder="0706600127" type="tel" name="phoneNumber"
                           value="<%=candidate.getPhoneNumber()%>" tabindex="4" required>
                    <div class="errorMessage">  
                        <%= candidateError.getPhoneNumberError()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="email">Email</label>
                    <input placeholder="abc@xyz.com" type="email" name="email" 
                           value="<%=candidate.getEmail()%>" tabindex="5" required>
                    <div class="errorMessage">  
                        <%= candidateError.getEmailError()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="address">Address</label>
                    <input placeholder="Phường Tân Phú, Quận 9, Thành phố Hồ Chí Minh, Vietnam"
                           type="text" name="address" value="<%=candidate.getAddress()%>" 
                           tabindex="6" required>
                    <div class="errorMessage">  
                        <%= candidateError.getAddressError()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="humanId">Human Id</label>
                    <input placeholder="xxxx xxxx xxxx" type="text" name="humanId" 
                           value="<%=candidate.getHumanId()%>" tabindex="7" required>
                    <div class="errorMessage">  
                        <%= candidateError.getHumanIdEror()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="nationality">Nationality</label>
                    <input placeholder="Viet Nam" type="text" name="nationality"
                           value="<%=candidate.getNationality()%>" tabindex="8" required>
                    <div class="errorMessage">  
                        <%= candidateError.getNationalityError()%>                       
                    </div>
                </fieldset>
                <fieldset>
                    <label for="notation">Notation</label> <br/>
                    <input placeholder="Type your Message Here...." type="text" 
                           name="notation" value="<%=candidate.getNotation()%>" 
                           tabindex="9" ></input>
                    <div class="errorMessage">  
                        <%= candidateError.getNotationError()%>                       
                    </div>                     
                </fieldset>
                <fieldset>
                    <label for="image">Image</label><br/>
                    <input type="file" name="image" tabindex="10" >
                    <div class="errorMessage">  
                        <%= candidateError.getImageError()%>                       
                    </div>      
                </fieldset>
                <fieldset>
                    <input name="candidateID" type="hidden" value="<%=candidate.getId()%>">
                    <input type="hidden" name="creatorID" value="<%=candidate.getCreatorID() %>" >
                    <input type="hidden" name="action" value="Update Candidate" >
                    <input type="RESET" id="contact-submit">
                    <input type="submit" id="contact-submit" value="UPDATE" >
                </fieldset>
                <div class="errorMessage">  
                    <%= candidateError.getMessageError()%>                       
                </div>
            </form>
        </div>
    </body>
</html>
