/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package OverTimeLog;

import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
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
@WebServlet(name = "ReportOverTimeController", urlPatterns = {"/ReportOverTimeController"})
public class ReportOverTimeController extends HttpServlet {

    public static String ERROR = "error.jsp";
    public static String SUCCESS = "create-overtime-report.jsp";

    private static String SUCCESS_MESSAGE = "Report created successfully";
    private static String ERROR_MESSAGE = "Error at Create Report";
    private static String EXIST_DATE_REPORT = "This Date has been reported";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String reason = request.getParameter("reason");
            float otHours = Float.parseFloat(request.getParameter("otHours"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateOT = new java.sql.Date(format.parse(request.getParameter("dateOT")).getTime());

            CreateUpdateOverTimeDAO overTimeDAO = new CreateUpdateOverTimeDAO();
            OverTimeReport_DTO report = null;
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");

            //check DateOT nay co duoc report truoc do chua
            boolean checkExistDateOfEmployee = overTimeDAO.checkExistDateOfEmployee(dateOT, userLogin.getEmployeeId());
            if (checkExistDateOfEmployee == true) {
                request.setAttribute("MESSAGE", EXIST_DATE_REPORT);
            } else {
                report = overTimeDAO.returnReportObject(dateOT, otHours, reason, userLogin.getEmployeeId());
                boolean checkCreateReport = overTimeDAO.createReport(report);
                if (checkCreateReport == true) {
                    request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ReportOverTimeController" + e.toString());
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
