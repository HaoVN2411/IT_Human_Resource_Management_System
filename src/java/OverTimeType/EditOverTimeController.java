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
@WebServlet(name = "EditOverTimeController", urlPatterns = {"/EditOverTimeController"})
public class EditOverTimeController extends HttpServlet {

    public static String EXIST_DATE_REPORT = "This Date has been reported";
    public static String SUCCESS_MESSAGE = "Edited Successfully";
    public static String FAIL_MESSAGE = "Edited Failed";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String reason = request.getParameter("reason");
            float otHours = Float.parseFloat(request.getParameter("otHours"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOT = new java.sql.Date(format.parse(request.getParameter("dateOT")).getTime());

            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            OverTimeReport_DTO reportIsEditing = (OverTimeReport_DTO) session.getAttribute("EDIT_OVERTIME_REPORT");
            OverTimeReport_DAO overTimeDAO = new OverTimeReport_DAO();

            OverTimeReport_DTO reportAfterEdited = new OverTimeReport_DTO();
            boolean checkEdit = false;

            //Dùng cách này để nếu không đổi ngày thì sẽ không cần phải query data nhieu lan
            if (!dateOT.equals(reportIsEditing.getDateOT())) {
                if (overTimeDAO.checkExistDateOfEmployee(dateOT, userLogin.getEmployeeId()) == true) {
                    request.setAttribute("MESSAGE", EXIST_DATE_REPORT);
                } else {
                    reportAfterEdited = overTimeDAO.returnReportObject(dateOT, otHours, reason, userLogin.getEmployeeId());
                    checkEdit = overTimeDAO.editOverTimeReport(reportIsEditing.getOverTimeId(), reportAfterEdited);
                    if (checkEdit == true) {
                        request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                    } else {
                        request.setAttribute("MESSAGE", FAIL_MESSAGE);
                    }
                }
            } else {
                reportAfterEdited = overTimeDAO.returnReportObject(dateOT, otHours, reason, userLogin.getEmployeeId());
                checkEdit = overTimeDAO.editOverTimeReport(reportIsEditing.getOverTimeId(), reportAfterEdited);
                if (checkEdit == true) {
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                } else {
                    request.setAttribute("MESSAGE", FAIL_MESSAGE);
                }
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
