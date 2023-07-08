/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Employee_Info;

import User_Login_Controller.User_Login_DTO;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author flami
 */
@WebServlet(name = "SearchEmployeeController", urlPatterns = {"/SearchEmployeeController"})
public class SearchEmployeeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "searchEmployee.jsp";
    private final static String SUCCESS = "searchEmployee.jsp";
    private final static String CHUYEN_TRANG = "mainHRS.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String statusEmployee;
        String url = ERROR;
        try {
            String search = ((String) request.getParameter("search")).trim();
            if (search == null) {
                search = "";
            }
            statusEmployee = request.getParameter("statusActive");
            if (!statusEmployee.equals("Not Working")
                    && !statusEmployee.equals("Working")
                    && !statusEmployee.equals("All")) {
                throw new Exception();
            }
            //get logining user
            String userID;
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            userID = userLogin.getEmployeeId();

            Employee_Info_DAO employeeDAO = new Employee_Info_DAO();
            //get list employee from database
            List<Employee_Info_DTO> listEmployee = employeeDAO.getListEmployee(search, statusEmployee, userID);
            if (!listEmployee.isEmpty()) {
                request.setAttribute("LIST_EMPLOYEE", listEmployee);
                url = SUCCESS;
            }
            request.setAttribute("STATUS_CONTRACT", statusEmployee);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.setAttribute("URL", url);
            request.getRequestDispatcher(CHUYEN_TRANG).forward(request, response);
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
