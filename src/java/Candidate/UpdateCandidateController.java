/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Candidate;

import User_Login_Controller.User_Login_DTO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flami This servlet class update candidate, only update candidate when
 * status contract this candidate is 'REJECTED'
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "UpdateCandidateController", urlPatterns = {"/UpdateCandidateController"})
public class UpdateCandidateController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "createCandidate.jsp";
    private final static String SUCCESS = "searchCandidate.jsp";
    private final static String URL = "main.jsp";
    private final static String SAVE_DIR = "imageCandidate";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        boolean check = true;
        String messageError;
        CandidateDTO candidate = null;

        try {
            CandidateDAO dao = new CandidateDAO();
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
            String creatorID = request.getParameter("creatorID");

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
            // get relative path
            String path = getServletContext().getRealPath("/");
            String imagePath = Helper.getPath(candidateID, SAVE_DIR);

            candidate = new CandidateDTO(candidateID, fullName, gender, dateOfBirth,
                    phoneNumber, email, address, humanId, nationality, notation, imagePath, creatorID, isActive);
            if (check) {
                if (file != null) {
                    //save img of candidate
                    boolean check2 = Helper.saveImage(candidateID, file, path, SAVE_DIR);
                }
                if (dao.updateCandidate(candidate)) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("CANDIDATE", candidate);
                request.setAttribute("CANDIDATE_ERROR", candidateError);
                url = ERROR;
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
