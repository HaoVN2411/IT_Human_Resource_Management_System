/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package overtime;

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
import userlogin.User_Login_DTO;

/**
 *
 * @author Hào Cute
 */
@WebServlet(name = "ViewManagedEmployeeOverTimeReportController", urlPatterns = {"/ViewManagedEmployeeOverTimeReportController"})
public class ViewManagedEmployeeOverTimeReportController extends HttpServlet {

    //HRM HRS
    public static String ERROR = "error.jsp";
    public static String SUCCESS = "/overtime/view-managed-employee-overtime-report.jsp";
    public static String EMPTY_LIST = "Don't have any report";
    String URL = ERROR;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String searchOverTimeReportById = request.getParameter("searchOverTimeReportById");
            String stringSearchOverTimeReportByDate = request.getParameter("searchOverTimeReportByDate");
            if (searchOverTimeReportById == null) {
                searchOverTimeReportById = "";
            }
            if (stringSearchOverTimeReportByDate == null) {
                stringSearchOverTimeReportByDate = "";
            }

            //lấy user của người quản lý
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");

            ViewManagedEmployeeOverTimeReportDAO DAO = new ViewManagedEmployeeOverTimeReportDAO();
            List<OverTimeReport_DTO> listManagedEmployeeOverTimeReport = new ArrayList<>();

            if (stringSearchOverTimeReportByDate.length() == 0) {
                listManagedEmployeeOverTimeReport = DAO.listManagedEmployeeOverTimeReport(searchOverTimeReportById, userLogin.getEmployeeId());
            } else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date searchOverTimeReportByDate = new java.sql.Date(format.parse(stringSearchOverTimeReportByDate).getTime());
                listManagedEmployeeOverTimeReport = DAO.listManagedEmployeeOverTimeReport(searchOverTimeReportById, searchOverTimeReportByDate, userLogin.getEmployeeId());
            }
            if (listManagedEmployeeOverTimeReport.size() > 0) {
                request.setAttribute("LIST_MANAGED_EMPLOYEE_OVERTIME_REPORT", listManagedEmployeeOverTimeReport);
            } else {
                request.setAttribute("LIST_MANAGED_EMPLOYEE_OVERTIME_REPORT", listManagedEmployeeOverTimeReport);
                request.setAttribute("MESSAGE", EMPTY_LIST);
            }
            url = SUCCESS;
            if (userLogin.getRoleName().equalsIgnoreCase("HRS")) {
                URL = "main/mainHRS.jsp";
            } else if (userLogin.getRoleName().equalsIgnoreCase("HRM")) {
                URL = "main/mainHRM.jsp";
            }
        } catch (Exception e) {
            log("Error at ViewManagedEmployeeOverTimeReportController" + e.toString());
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
