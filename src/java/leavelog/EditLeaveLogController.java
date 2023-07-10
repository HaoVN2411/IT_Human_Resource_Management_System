/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leavelog;

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
import overtime.CreateUpdateOverTimeDAO;
import userlogin.User_Login_DTO;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "EditLeaveLogController", urlPatterns = {"/EditLeaveLogController"})
public class EditLeaveLogController extends HttpServlet {

    //HRS and Staff
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "/leavelog/Edit-leavelog-apply.jsp";

    public static String EXIST_DATE_REPORT = "This Date has been booked";
    public static String SUCCESS_MESSAGE = "Edited Successfully";
    public static String FAIL_MESSAGE = "Edited Failed";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String URL = ERROR;
        try {
            String reason = request.getParameter("reason");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateLeave = new java.sql.Date(format.parse(request.getParameter("dateLeave")).getTime());

            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            LeaveLog_DTO applicationIsEditing = (LeaveLog_DTO) session.getAttribute("EDIT_LEAVELOG_APPLY");
            CreateLeaveLogDAO checkExistedApply = new CreateLeaveLogDAO();

            LeaveLog_DTO applicationAfterEdited = new LeaveLog_DTO(dateLeave, reason, userLogin.getEmployeeId());
            boolean checkEdit = false;

            EditLeaveLogDAO editLeaveLogDAO = new EditLeaveLogDAO();

            //Dùng cách này để nếu không đổi ngày thì sẽ không cần phải check exist Day
            if (!dateLeave.equals(applicationIsEditing.getDateLeave())) {
                if (checkExistedApply.checkExistDateOfEmployee(dateLeave, userLogin.getEmployeeId()) == true) {
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
                            checkEdit = editLeaveLogDAO.editLeaveLogApply(applicationIsEditing.getLeaveLogID(), applicationAfterEdited);
                            if (checkEdit == true) {
                                request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                            }
                        }
                    }
                }
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
                        checkEdit = editLeaveLogDAO.editLeaveLogApply(applicationIsEditing.getLeaveLogID(), applicationAfterEdited);
                        if (checkEdit == true) {
                            request.setAttribute("MESSAGE", SUCCESS_MESSAGE);
                        }
                    }
                }
            }
            url = SUCCESS;
            session.removeAttribute("EDIT_LEAVELOG_APPLY");
            if (userLogin.getRoleName().equalsIgnoreCase("HRS")) {
                URL = "main/mainHRS.jsp";
            } else if (userLogin.getRoleName().equalsIgnoreCase("Staff")) {
                URL = "main/mainStaff.jsp";
            }
        } catch (Exception e) {
            log("Error at EditLeaveLogController" + e.toString());
        } finally {
            request.setAttribute("URL", url);
            request.getRequestDispatcher(URL).forward(request, response);
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
