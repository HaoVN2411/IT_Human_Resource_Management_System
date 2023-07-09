/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package candidate;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flami
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
@WebServlet(name = "CandidateController", urlPatterns = {"/CandidateController"})
public class CandidateController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String USER_LOGIN = "Login";
    private static final String USER_LOGIN_CONTROLLER = "LoginController";

    private static final String CANDIDATE_CREATE = "Create Candidate";
    private static final String CANDIDATE_CREATE_CONTROLLER = "CreateCandidateController";

    private static final String CANDIDATE_SEARCH = "Search Candidate";
    private static final String CANDIDATE_SEARCH_CONTROLLER = "SearchCandidateController";

    private static final String SHOW_DETAIL_CANDIDATE = "Show Candidate Detail";
    private static final String SHOW_DETAIL_CANDIDATE_CONTROLLER = "ShowCandidateDetailController";

    private static final String UPDATE_CANDIDATE = "Update Candidate";
    private static final String UPDATE_CANDIDATE_CONTROLLER = "UpdateCandidateController";

    private static final String UPDATE_CANDIDATE_FORM = "Update";
    private static final String UPDATE_CANDIDATE_FORM_CONTROLLER = "ShowUpdateCandidateFormController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (USER_LOGIN.equals(action)) {
                url = USER_LOGIN_CONTROLLER;
            } else if (CANDIDATE_CREATE.equals(action)) {
                url = CANDIDATE_CREATE_CONTROLLER;
            } else if (CANDIDATE_SEARCH.equals(action)) {
                url = CANDIDATE_SEARCH_CONTROLLER;
            } else if (SHOW_DETAIL_CANDIDATE.equals(action)) {
                url = SHOW_DETAIL_CANDIDATE_CONTROLLER;
            } else if (UPDATE_CANDIDATE.equals(action)) {
                url = UPDATE_CANDIDATE_CONTROLLER;
            } else if (UPDATE_CANDIDATE.equals(action)) {
                url = SHOW_DETAIL_CANDIDATE_CONTROLLER;
            }else if (UPDATE_CANDIDATE_FORM.equals(action)) {
                url = UPDATE_CANDIDATE_FORM_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
