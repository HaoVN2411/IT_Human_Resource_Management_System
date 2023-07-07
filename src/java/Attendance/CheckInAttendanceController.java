/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance;

import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
@WebServlet(name = "CheckInAttendanceController", urlPatterns = {"/CheckInAttendanceController"})
public class CheckInAttendanceController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "CheckAttendance.jsp";
    private static String CHECKIN_SUCCESS = "CheckIn Successfully!";
    private static String CHECKOUT_SUCCESS = "CheckOut Successfully";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            boolean check = true;
            LocalDateTime DateTime = LocalDateTime.now();
            LocalDate localDate = DateTime.toLocalDate();     
            DayOfWeek dayOfWeek = localDate.getDayOfWeek();       
            HttpSession session = request.getSession();
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            Attendance_DAO dao = new Attendance_DAO();
            AttendanceService service = new AttendanceService();
             
            float endHour = 0.0f;
            float startHour = 0.0f;
            if (check) {
                boolean attendanceExists = dao.checkAttendanceExists(loginUser.getEmployeeId(), localDate);
                if (attendanceExists) {
                    dao.updateAttendance(DateTime, loginUser.getEmployeeId(), localDate);
                    Attendance_DTO attendance = dao.getOffHour(loginUser.getEmployeeId(), localDate);
                    startHour = attendance.getStartHours();
                    endHour = attendance.getEndHours();
                    float offHour = service.CalculateOfficeHour(startHour, endHour);
                    float totalHour = service.CalculateTotalHour(startHour, endHour);
                    DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                    DecimalFormat decimalFormat = new DecimalFormat("#0.00", symbols);
                    decimalFormat.setParseBigDecimal(true);
                    if(dayOfWeek.equals("SUNDAY") || dayOfWeek.equals("SATURDAY") || dao.holidayType(Date.valueOf(localDate)) != null){
                        offHour = 0.0f;
                        totalHour = decimalFormat.parse(decimalFormat.format(totalHour)).floatValue();
                    } else {
                        offHour = decimalFormat.parse(decimalFormat.format(offHour)).floatValue();
                        totalHour = decimalFormat.parse(decimalFormat.format(totalHour)).floatValue();
                    }
                    
                    dao.insertOffHour(offHour, totalHour, loginUser.getEmployeeId(), localDate);
                    request.setAttribute("MESSAGE", CHECKOUT_SUCCESS);
                } else {
                    dao.getStartHours(DateTime, loginUser.getEmployeeId());
                    request.setAttribute("MESSAGE", CHECKIN_SUCCESS);
                }

                url = SUCCESS;
                check = false;
            }

        } catch (Exception ex) {
            log("Error at Controller: " + ex.toString());
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
