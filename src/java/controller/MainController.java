/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hào Cute
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String USER_LOGIN = "Login";
    private static final String USER_LOGIN_CONTROLLER = "LoginController";
//
//    private static final String CANDIDATE_CREATE = "Create Candidate";
//    private static final String CANDIDATE_CREATE_CONTROLLER = "CreateCandidateController";
//
//    private static final String CANDIDATE_SEARCH = "Search Candidate";
//    private static final String CANDIDATE_SEARCH_CONTROLLER = "SearchCandidateController";
//
//    private static final String SHOW_DETAIL_CANDIDATE = "Show Candidate Detail";
//    private static final String SHOW_DETAIL_CANDIDATE_CONTROLLER = "ShowCandidateDetailController";
//
//    private static final String UPDATE_CANDIDATE = "Update Candidate";
//    private static final String UPDATE_CANDIDATE_CONTROLLER = "UpdateCandidateController";

    
    
//    private static final String CREATE_CANDIDATE_CONTRACT = "Create Contract";
//    private static final String CREATE_CANDIDATE_CONTRACT_CONTROLLER = "CreateContractController";
//
//    private static final String SEARCH_CONTRACT = "Search Contract";
//    private static final String SEARCH_CONTRACT_CONTROLLER = "SearchContractController";
//
//    private static final String SHOW_TEMPORARY_CONTRACT_DETAIL = "Show Detail Temporary Contract";
//    private static final String SHOW_TEMPORARY_CONTRACT_DETAIL_CONTROLLER = "ShowTemporaryContractDetailController";
//
//    private static final String PRINT_CONTRACT = "Print Contract";
//    private static final String PRINT_CONTRACT_CONTROLLER = "PrintContractController";
//
//    private static final String APPROVE_CONTRACT = "Approve Contract";
//    private static final String REJECT_CONTRACT = "Reject Contract";
//    private static final String DECISION_CONTRACT_CONTROLLER = "DecisionContractController";
//
//    private static final String UPDATE_CONTRACT = "Update Contract";
//    private static final String UPDATE_CONTRACT_CONTROLLER = "UpdateTemporaryCantractController";
//
//    private static final String APPLY_CONTRACT = "Apply Contract";
//    private static final String APPLY_CONTRACT_CONTROLLER = "ApplyTemporaryContract";

//    private static final String SHOW_INFORMATION = "Show Information";
//    private static final String SHOW_INFORMATION_CONTROLLER = "ViewInformationController";
//
//    private static final String VIEW_EMPLOYEE_CONTRACT = "View Employee Contract Detail";
//    private static final String VIEW_EMPLOYEE_CONTRACT_CONTROLLER = "ViewEmployeeContractController";
//
//    private static final String SEARCH_EMPLOYEE = "Search Employee";
//    private static final String SEARCH_EMPLOYEE_CONTROLLER = "SearchEmployeeController";
//    
//    private static final String SHOW_EMPLOYEE_DETAIL = "Show Employee Detail";
//    private static final String SHOW_EMPLOYEE_DETAIL_CONTROLLER = "ShowEmployeeDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
