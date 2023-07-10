/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtime;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "OverTimeController", urlPatterns = {"/OverTimeController"})
public class OverTimeController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    
    private static final String CREATE_OVERTIME_REPORT = "CreateOverTimeReport";
    private static final String CREATE_OVERTIME_CONTROLLER = "ReportOverTimeController";
    private static final String VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT = "ViewManagedEmployeeOverTimeReport";
    private static final String VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT_CONTROLLER = "ViewManagedEmployeeOverTimeReportController";
    private static final String VIEW_PERSON_OVERTIME_REPORT = "ViewPersonOverTimeReport";
    private static final String VIEW_PERSON_OVERTIME_REPORT_CONTROLLER = "ViewPersonOverTimeReportController";
    private static final String CONFIRM_OVERTIME = "ConfirmOverTime";
    private static final String CONFIRM_OVERTIME_CONTROLLER = "ConfirmOverTimeController";
    private static final String EDIT_OVERTIME_BUTTON = "EditOverTimeButton";
    private static final String EDIT_OVERTIME_BUTTON_CONTROLLER = "EditOverTimeButtonController";
    private static final String EDIT_OVERTIME = "EditOverTime";
    private static final String EDIT_OVERTIME_CONTROLLER = "EditOverTimeController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (CREATE_OVERTIME_REPORT.equals(action)) {
                url = CREATE_OVERTIME_CONTROLLER;
            }
            else if (VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT.equals(action)) {
                url = VIEW_MANAGED_EMPLOYEE_OVERTIME_REPORT_CONTROLLER;
            }
            else if (VIEW_PERSON_OVERTIME_REPORT.equals(action)) {
                url = VIEW_PERSON_OVERTIME_REPORT_CONTROLLER;
            }
            else if (CONFIRM_OVERTIME.equals(action)) {
                url = CONFIRM_OVERTIME_CONTROLLER;
            }
            else if (EDIT_OVERTIME_BUTTON.equals(action)) {
                url = EDIT_OVERTIME_BUTTON_CONTROLLER;
            }
            else if (EDIT_OVERTIME.equals(action)) {
                url = EDIT_OVERTIME_CONTROLLER;
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
