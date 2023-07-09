/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package employee;

import userlogin.User_Login_DTO;
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
@WebServlet(name = "ViewEmployeeContractController", urlPatterns = {"/ViewEmployeeContractController"})
public class ViewEmployeeContractController extends HttpServlet {

    private static final String ERROR = "SearchEmployeeController";
    private static final String SUCCESS = "/employee/viewEmployeeContract.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        String URL = ERROR;
        try {
            String employeeID = request.getParameter("employeeID");
            Employee_Info_DAO dao = new Employee_Info_DAO();
            List<ContractDTO> listContract = dao.getListEmployeeContract(employeeID);
            if (!listContract.isEmpty()) {
                request.setAttribute("EMPLOYEE_CONTRACT", listContract);
                url = SUCCESS;
                //Reload page
                HttpSession session = request.getSession();
                User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
                if (userLogin.getRoleName().equals("HRS")) {
                    URL = "main/mainHRS.jsp";
                } else if (userLogin.getRoleName().equals("HRM")) {
                    URL = "main/mainHRM.jsp";
                } else {
                    URL = "main/mainStaff.jsp";
                }
            } else {
                request.setAttribute("ERROR_MESSAGE", "Do not have contract of this employee!");
            }
        } catch (Exception e) {
            log("error at SearchController" + e.toString());
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
