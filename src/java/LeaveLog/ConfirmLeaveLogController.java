/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaveLog;

import OverTimeLog.CreateUpdateOverTimeDAO;
import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "ConfirmLeaveLogController", urlPatterns = {"/ConfirmLeaveLogController"})
public class ConfirmLeaveLogController extends HttpServlet {

    //HRM and HRS
    
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "ViewManagedEmployeeLeaveLogApplicationController";

    public static String APPROVED = "Approve";
    public static String REJECTED = "Reject";
    public static String UNKNOW_ERROR = "Unknown Error";
    public static String WRONG_MANAGER_CONFIRM = "You cannot approve or reject this employee";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String leaveLogID = request.getParameter("leaveLogID");
            String employeeId = request.getParameter("employeeId");
            String confirm = request.getParameter("confirm");

            HttpSession session = request.getSession();
            CreateUpdateOverTimeDAO checkManagedByUserLogin = new CreateUpdateOverTimeDAO();
            ConfirmLeaveLogDAO LeaveLogDAO = new ConfirmLeaveLogDAO();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (checkManagedByUserLogin.isEmployeeManagedByUserLogin(userLogin.getEmployeeId(), employeeId)) {
                boolean isConfirm = false;
                if (confirm.equals(APPROVED)) {
                    isConfirm = LeaveLogDAO.confirmLeaveLog(leaveLogID, true);
                } else if (confirm.equals(REJECTED)) {
                    isConfirm = LeaveLogDAO.confirmLeaveLog(leaveLogID, false);
                }
                if (isConfirm == true) {
                    url = SUCCESS;
                }
            }
        } catch (Exception e) {
            log("Error at ConfirmLeaveLogController" + e.toString());
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
