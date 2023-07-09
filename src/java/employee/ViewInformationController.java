/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package employee;

import userlogin.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ViewInformationController", urlPatterns = {"/ViewInformationController"})
public class ViewInformationController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "employee/viewEmployeeInformation.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        User_Login_DTO loginUser = null;
        try {
            Employee_Info_DAO dao = new Employee_Info_DAO();

            HttpSession session = request.getSession();
            loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            String employeeID = loginUser.getEmployeeId();

            Employee_Info_DTO user = dao.getUserInformation(employeeID);
            if (user != null) {
                request.setAttribute("USER_INFO", user);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("error at SearchController" + e.toString());
        } finally {
            //request.setAttribute("URL", url);
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
