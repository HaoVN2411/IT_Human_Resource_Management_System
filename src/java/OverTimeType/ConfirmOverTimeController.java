/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OverTimeType;

import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ConfirmOverTimeController", urlPatterns = {"/ConfirmOverTimeController"})
public class ConfirmOverTimeController extends HttpServlet {

    public static String APPROVED = "Approved";
    public static String REJECTED = "Rejected";
    public static String UNKNOW_ERROR = "Error at ConfirmOverTimeController";
    public static String WRONG_ROLE_CONFIRM = "You cannot approve or reject this employee";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String date = request.getParameter("dateOT");
            String employeeId = request.getParameter("employeeId");
            String reasonReject = request.getParameter("reasonReject");
            String confirm = request.getParameter("confirm");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOT = new java.sql.Date(format.parse(date).getTime());

            HttpSession session = request.getSession();
            OverTimeReport_DAO overTime = new OverTimeReport_DAO();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (overTime.isEmployeeManagedByUserLogin(userLogin.getEmployeeId(), employeeId)) {
                boolean isConfirm = false;
                if (confirm.equals(APPROVED)) {
                    isConfirm = overTime.confirmOverTime(dateOT, employeeId, reasonReject, true);
                } else if (confirm.equals(REJECTED)) {
                    isConfirm = overTime.confirmOverTime(dateOT, employeeId, reasonReject, false);
                }
                if (isConfirm == false) {
                    request.setAttribute("MESSAGE", UNKNOW_ERROR);
                }
            } else {
                request.setAttribute("MESSAGE", WRONG_ROLE_CONFIRM);
            }

        } catch (Exception e) {
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
