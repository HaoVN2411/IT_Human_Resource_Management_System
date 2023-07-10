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
import userlogin.User_Login_DTO;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "EditLeaveLogButtonController", urlPatterns = {"/EditLeaveLogButtonController"})
public class EditLeaveLogButtonController extends HttpServlet {

    //HRS and Staff
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "/leavelog/Edit-leavelog-apply.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String URL = ERROR;
        try {
            int leaveLogID = Integer.parseInt(request.getParameter("leaveLogID"));
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date dateLeave = new java.sql.Date(format.parse(request.getParameter("dateLeave")).getTime());

            LeaveLog_DTO leaveLogApplication = new LeaveLog_DTO(leaveLogID, dateLeave);

            HttpSession session = request.getSession();
            session.setAttribute("EDIT_LEAVELOG_APPLY", leaveLogApplication);
            url = SUCCESS;

            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");

            if (userLogin.getRoleName().equalsIgnoreCase("HRS")) {
                URL = "main/mainHRS.jsp";
            } else if (userLogin.getRoleName().equalsIgnoreCase("Staff")) {
                URL = "main/mainStaff.jsp";
            }
        } catch (Exception e) {
            log("Error at EditLeaveLogButtonController" + e.toString());
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
