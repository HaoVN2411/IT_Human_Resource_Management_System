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
import java.text.SimpleDateFormat;
import java.util.AbstractList;
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
@WebServlet(name = "ViewPersonOverTimeReportController", urlPatterns = {"/ViewPersonOverTimeReportController"})
public class ViewPersonOverTimeReportController extends HttpServlet {

    //HRS and HRM
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "view-personal-overtime-report.jsp";

    public static String EMPTY_LIST = "Don't have any report";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String stringSearchOverTimeReportByDate = request.getParameter("searchOverTimeReportByDate");
            if (stringSearchOverTimeReportByDate == null){
                stringSearchOverTimeReportByDate = "";
            }
            
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            
            ViewPersonOverTimeReportDAO DAO = new ViewPersonOverTimeReportDAO();
            List<OverTimeReport_DTO> listPersonOverTimeReport = new ArrayList<>();
            if (stringSearchOverTimeReportByDate.length() == 0) {
                listPersonOverTimeReport = DAO.listPersonOverTimeReport(userLogin.getEmployeeId());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date searchOverTimeReportByDate = new java.sql.Date(format.parse(stringSearchOverTimeReportByDate).getTime());
                listPersonOverTimeReport = DAO.listPersonOverTimeReport(searchOverTimeReportByDate, userLogin.getEmployeeId());
            }
            if (listPersonOverTimeReport.size() > 0) {
                request.setAttribute("LIST_PERSON_OVERTIME_REPORT", listPersonOverTimeReport);
            } else {
                request.setAttribute("MESSAGE", EMPTY_LIST);
            }
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at ViewPersonOverTimeReportController" + e.toString());
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
