/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package employee;

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
@WebServlet(name = "EmployeeController", urlPatterns = {"/EmployeeController"})
public class EmployeeController extends HttpServlet {

    private static final String ERROR = "error.jsp";

    private static final String SHOW_INFORMATION = "Show Information";
    private static final String SHOW_INFORMATION_CONTROLLER = "ViewInformationController";

    private static final String VIEW_EMPLOYEE_CONTRACT = "View Employee Contract Detail";
    private static final String VIEW_EMPLOYEE_CONTRACT_CONTROLLER = "ViewEmployeeContractController";

    private static final String SEARCH_EMPLOYEE = "Search Employee";
    private static final String SEARCH_EMPLOYEE_CONTROLLER = "SearchEmployeeController";

    private static final String SHOW_EMPLOYEE_DETAIL = "Show Employee Detail";
    private static final String SHOW_EMPLOYEE_DETAIL_CONTROLLER = "ShowEmployeeDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (SHOW_INFORMATION.equals(action)) {
                url = SHOW_INFORMATION_CONTROLLER;
            } else if (VIEW_EMPLOYEE_CONTRACT.equals(action)) {
                url = VIEW_EMPLOYEE_CONTRACT_CONTROLLER;
            } else if (SEARCH_EMPLOYEE.equals(action)) {
                url = SEARCH_EMPLOYEE_CONTROLLER;
            } else if (SHOW_EMPLOYEE_DETAIL.equals(action)) {
                url = SHOW_EMPLOYEE_DETAIL_CONTROLLER;
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
