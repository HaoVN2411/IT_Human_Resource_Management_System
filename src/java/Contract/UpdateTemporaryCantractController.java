/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Contract;

import Candidate.CandidateDTO;
import Candidate.CandidateDAO;
import Candidate.CandidateError;
import Candidate.Helper;
import Candidate.ValidationInput;
import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author flami This class use to update temporary contract
 */
@WebServlet(name = "UpdateTemporaryCantractController", urlPatterns = {"/UpdateTemporaryCantractController"})
public class UpdateTemporaryCantractController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "updateContract.jsp";
    private final static String SUCCESS = "searchContract.jsp";
    private final static String SAVE_DIR = "imageCandidate";
    private final static String URL = "main.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        boolean check = true;
        String messageError;
        CandidateDTO candidate = null;

        try {
            //Candidate
            CandidateDAO daoCandidate = new CandidateDAO();
            String candidateID = request.getParameter("candidateID");
            String fullName = request.getParameter("fullName").trim();
            String gender = request.getParameter("gender").trim();

            String dateString = request.getParameter("dateOfBirth").trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateOfBirth = LocalDate.parse(dateString, formatter);

            String phoneNumber = request.getParameter("phoneNumber").trim();
            String email = request.getParameter("email").trim();
            String address = request.getParameter("address").trim();
            String humanId = request.getParameter("humanId").trim();
            String nationality = request.getParameter("nationality").trim();
            String notation = request.getParameter("notation").trim();
            Part file = request.getPart("image");
            String creatorID = daoCandidate.getACandidate(candidateID).getCreatorID();
            if (file.getSubmittedFileName().equals("")) {
                file = null;
            }

            boolean isActive = true;

            CandidateError candidateError = new CandidateError();
            ValidationInput errorChecking = new ValidationInput();

            //check full name of candidate
            if (!errorChecking.isFullName(fullName)) {
                messageError = errorChecking.getMessage();
                candidateError.setFullNameError(messageError);
                check = false;
            }
            //check phone number 
            if (!errorChecking.isPhoneNumber(phoneNumber)) {
                messageError = errorChecking.getMessage();
                candidateError.setPhoneNumberError(messageError);
                check = false;
            }
            //check email
            if (!errorChecking.isEmail(email)) {
                messageError = errorChecking.getMessage();
                candidateError.setEmailError(messageError);
                check = false;
            }
            //check address
            if (!errorChecking.isAddress(address)) {
                messageError = errorChecking.getMessage();
                candidateError.setAddressError(messageError);
                check = false;
            }
            //check humanId
            if (!errorChecking.isHumanId(humanId)) {
                messageError = errorChecking.getMessage();
                candidateError.setHumanIdEror(messageError);
                check = false;
            }
            //check nationality
            if (!errorChecking.isNationality(nationality)) {
                messageError = errorChecking.getMessage();
                candidateError.setNationalityError(messageError);
                check = false;
            }
            //check notation
            if (!errorChecking.isNotation(notation)) {
                messageError = errorChecking.getMessage();
                candidateError.setNotationError(messageError);
                check = false;
            }
            //check img
            if (file != null && !errorChecking.isImageFile(file)) {
                messageError = errorChecking.getMessage();
                candidateError.setImageError(messageError);
                check = false;
            }
            //get relative path
            String path = getServletContext().getRealPath("/");
            String imagePath = Helper.getPath(candidateID, SAVE_DIR);//path contain img

            candidate = new CandidateDTO(candidateID, fullName, gender, dateOfBirth,
                    phoneNumber, email, address, humanId, nationality, notation,
                    imagePath, creatorID, isActive);

            ContractError error = new ContractError();
            ContractDAO daoCont = new ContractDAO();
            //format startDate 
            dateString = request.getParameter("startDate").trim();
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(dateString, formatter);

            String salaryString = request.getParameter("salary");
            String description = request.getParameter("discription");
            String contractID = request.getParameter("contractID");
            TemporaryContractDTO temp = daoCont.getTemporaryContract(contractID);
            float allowance = temp.getAllowance();
            String approverID = temp.getApproverID();
            float salary = 0;

            ValidationInputTemporaryContract validation = new ValidationInputTemporaryContract();
            //validate salary from inputing user
            if (!validation.isSalary(salaryString)) {
                String message = validation.getMessageError();
                error.setSalaryError(message);
                check = false;
            } else {
                salary = Float.parseFloat(salaryString);
            }
            //validate discription from inputing user
            if (!validation.isDescription(description)) {
                String message = validation.getMessageError();
                error.setNotationError(message);
                check = false;
            }

            TemporaryContractDTO contract = new TemporaryContractDTO(
                    contractID, startDate, salary, allowance,
                    approverID, creatorID, description,
                    candidateID, temp.getStatus(), temp.getReason());

            if (check) {
                if (file != null) {
                    //save img
                    Helper.saveImage(candidateID, file, path, SAVE_DIR);
                }
                if (daoCandidate.updateCandidate(candidate) && daoCont.updateTemporaryContract(contract)) {
                    url = SUCCESS;
                }
            } else {
                //return error message
                request.setAttribute("ERROR_CONTRACT", error);
                request.setAttribute("TEMPORARY_CONTRACT", contract);
                request.setAttribute("CANDIDATE", candidate);
                request.setAttribute("CANDIDATE_ERROR", candidateError);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("URL", url);
            request.getRequestDispatcher(URL).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
