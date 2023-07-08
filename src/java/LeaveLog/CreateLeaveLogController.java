/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaveLog;

import OverTimeLog.CreateUpdateOverTimeDAO;
import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.smartcardio.Card;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "CreateLeaveLogController", urlPatterns = {"/CreateLeaveLogController"})
public class CreateLeaveLogController extends HttpServlet {

    //Staff and HRS
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "create-leavelog-apply.jsp";

    private static String SUCCESS_MESSAGE = "Application Created successfully";
    private static String EXIST_DATE_REPORT = "This Date has been Existed";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String reason = request.getParameter("reason");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateLeave = new java.sql.Date(format.parse(request.getParameter("dateLeave")).getTime());

            CreateLeaveLogDAO LeaveLogDAO = new CreateLeaveLogDAO();
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");

            Calendar cal = Calendar.getInstance();
            cal.setTime(dateLeave);
            int monthOfdateLeave = cal.get(Calendar.MONTH) + 1;
            int yearOfdateLeave = cal.get(Calendar.YEAR);
            if (LeaveLogDAO.totalLeaveLogInYear(yearOfdateLeave, userLogin.getEmployeeId()) >= 12) {
                request.setAttribute("MESSAGE", "You cannot apply for leave log more than 12 days per year");
            } else if (LeaveLogDAO.totalLeaveLogInMonth(monthOfdateLeave, userLogin.getEmployeeId()) >= 3) {
                request.setAttribute("MESSAGE", "You cannot apply for leave log more than 3 days per month");
            } else {
                //check DateOT nay co duoc report truoc do chua
                boolean checkExistDateOfEmployee = LeaveLogDAO.checkExistDateOfEmployee(dateLeave, userLogin.getEmployeeId());
                if (checkExistDateOfEmployee == true) {
                    request.setAttribute("MESSAGE", EXIST_DATE_REPORT);
                } else {
                    CreateUpdateOverTimeDAO checkDateDAO = new CreateUpdateOverTimeDAO();
                    String checkDate = checkDateDAO.holidayType(dateLeave);
                    if (checkDate != null) {
                        request.setAttribute("MESSAGE", "This Day is a Holiday");
                    } else {
                        checkDate = checkDateDAO.checkWeekend(dateLeave);
                        if (checkDate != null) {
                            request.setAttribute("MESSAGE", "This Day is a Weekend");
                        } else {
                            LeaveLog_DTO LeaveLogApplication = new LeaveLog_DTO(dateLeave, reason, userLogin.getEmployeeId());
                            boolean checkCreateApplication = LeaveLogDAO.CreateLeaveLogApplication(LeaveLogApplication);
                            if (checkCreateApplication == true) {
                                request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                            }
                        }
                    }
                }
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at CreateLeaveLogController" + e.toString());
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
