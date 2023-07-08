/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaveLog;

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
@WebServlet(name = "LeaveLogController", urlPatterns = {"/LeaveLogController"})
public class LeaveLogController extends HttpServlet {
    
        private static final String ERROR = "error.jsp";
    
    private static final String CREATE_LEAVELOG_APPLICATION = "CreateLeaveLogApplication";//done
    private static final String CREATE_LEAVELOG_CONTROLLER = "CreateLeaveLogController";//done
    private static final String VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLICATION = "ViewManagedEmployeeLeaveLogApplication";//done
    private static final String VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLICATION_CONTROLLER = "ViewManagedEmployeeLeaveLogApplicationController";//done
    private static final String VIEW_PERSON_LEAVELOG_APPLICATION = "ViewPersonLeaveLogApplication";//done
    private static final String VIEW_PERSON_LEAVELOG_APPLICATION_CONTROLLER = "ViewPersonLeaveLogApplicationController";//done
    private static final String CONFIRM_LEAVELOG = "ConfirmLeaveLog";//done
    private static final String CONFIRM_LEAVELOG_CONTROLLER = "ConfirmLeaveLogController";//done
    private static final String EDIT_LEAVELOG_BUTTON = "EditLeaveLogButton";//done
    private static final String EDIT_LEAVELOG_BUTTON_CONTROLLER = "EditLeaveLogButtonController";//done
    private static final String EDIT_LEAVELOG = "EditLeaveLog";
    private static final String EDIT_LEAVELOG_CONTROLLER = "EditLeaveLogController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if (CREATE_LEAVELOG_APPLICATION.equals(action)) {
                url = CREATE_LEAVELOG_CONTROLLER;
            }
            else if (VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLICATION.equals(action)) {
                url = VIEW_MANAGED_EMPLOYEE_LEAVELOG_APPLICATION_CONTROLLER;
            }
            else if (VIEW_PERSON_LEAVELOG_APPLICATION.equals(action)) {
                url = VIEW_PERSON_LEAVELOG_APPLICATION_CONTROLLER;
            }
            else if (CONFIRM_LEAVELOG.equals(action)) {
                url = CONFIRM_LEAVELOG_CONTROLLER;
            }
            else if (EDIT_LEAVELOG_BUTTON.equals(action)) {
                url = EDIT_LEAVELOG_BUTTON_CONTROLLER;
            }
            else if (EDIT_LEAVELOG.equals(action)) {
                url = EDIT_LEAVELOG_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at LeaveLogController" + e.toString());
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
