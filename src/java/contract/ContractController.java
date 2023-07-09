/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package contract;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flami
 */
@WebServlet(name = "ContractController", urlPatterns = {"/ContractController"})
public class ContractController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String CREATE_CANDIDATE_CONTRACT = "Create Contract";
    private static final String CREATE_CANDIDATE_CONTRACT_CONTROLLER = "CreateContractController";

    private static final String SEARCH_CONTRACT = "Search Contract";
    private static final String SEARCH_CONTRACT_CONTROLLER = "SearchContractController";

    private static final String SHOW_TEMPORARY_CONTRACT_DETAIL = "Show Detail Temporary Contract";
    private static final String SHOW_TEMPORARY_CONTRACT_DETAIL_CONTROLLER = "ShowTemporaryContractDetailController";

    private static final String PRINT_CONTRACT = "Print Contract";
    private static final String PRINT_CONTRACT_CONTROLLER = "PrintContractController";

    private static final String APPROVE_CONTRACT = "Approve Contract";
    private static final String REJECT_CONTRACT = "Reject Contract";
    private static final String DECISION_CONTRACT_CONTROLLER = "DecisionContractController";

    private static final String UPDATE_CONTRACT = "Update Contract";
    private static final String UPDATE_CONTRACT_CONTROLLER = "UpdateTemporaryCantractController";

    private static final String APPLY_CONTRACT = "Apply Contract";
    private static final String APPLY_CONTRACT_CONTROLLER = "ApplyTemporaryContractController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (CREATE_CANDIDATE_CONTRACT.equals(action)) {
                url = CREATE_CANDIDATE_CONTRACT_CONTROLLER;
            } else if (SEARCH_CONTRACT.equals(action)) {
                url = SEARCH_CONTRACT_CONTROLLER;
            } else if (SHOW_TEMPORARY_CONTRACT_DETAIL.equals(action)) {
                url = SHOW_TEMPORARY_CONTRACT_DETAIL_CONTROLLER;
            } else if (PRINT_CONTRACT.equals(action)) {
                url = PRINT_CONTRACT_CONTROLLER;
            } else if (APPROVE_CONTRACT.equals(action)
                    || REJECT_CONTRACT.equals(action)) {
                url = DECISION_CONTRACT_CONTROLLER;
            } else if (UPDATE_CONTRACT.equals(action)) {
                url = UPDATE_CONTRACT_CONTROLLER;
            } else if (APPLY_CONTRACT.equals(action)) {
                url = APPLY_CONTRACT_CONTROLLER;
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
