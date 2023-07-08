/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeaveLog;

import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ViewPersonLeaveLogApplicationController", urlPatterns = {"/ViewPersonLeaveLogApplicationController"})
public class ViewPersonLeaveLogApplicationController extends HttpServlet {

    //HRS and HRM
    public static String EMPTY_LIST = "Don't have any report";
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "view-person-leavelog-report.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String stringSearchLeaveLogApplyByDate = request.getParameter("searchLeaveLogApplyByDate");
            if (stringSearchLeaveLogApplyByDate == null){
                stringSearchLeaveLogApplyByDate = "";
            }
            
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");

            ViewPersonLeaveLogApplicationDAO DAO = new ViewPersonLeaveLogApplicationDAO();
            List<LeaveLog_DTO> listPersonLeaveLogApply = new ArrayList<>();
            if (stringSearchLeaveLogApplyByDate.length() == 0) {
                listPersonLeaveLogApply = DAO.listPersonLeaveLogApply(userLogin.getEmployeeId());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date searchLeaveLogReportByDate = new java.sql.Date(format.parse(stringSearchLeaveLogApplyByDate).getTime());
                listPersonLeaveLogApply = DAO.listPersonLeaveLogApply(searchLeaveLogReportByDate, userLogin.getEmployeeId());
            }
            if (listPersonLeaveLogApply.size() > 0) {
                request.setAttribute("LIST_PERSON_LEAVELOG_APPLY", listPersonLeaveLogApply);
            } else {
                request.setAttribute("MESSAGE", EMPTY_LIST);
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewPersonLeaveLogApplicationController" + e.toString());
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
