/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PayRoll;

import Employee_Info.Employee_Info_DAO;
import Employee_Info.Employee_Info_DTO;
import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CAO-KIEN-QUOC
 */
@WebServlet(name = "PayRollController", urlPatterns = {"/PayRollController"})
public class PayRollController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String SUCCESS = "PayRoll.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            PayRoll_DAO dao = new PayRoll_DAO();
            HttpSession session = request.getSession();
            User_Login_DTO login = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            PayRoll_DTO officeHours = dao.getOfficeHours(login.getEmployeeId());
            final float BHXH = (float) 0.045;
            final float TNCN = (float) 0.05;
            final float BHTN = (float) 0.02;
            //Tinh Ot_Income
            float ot_Income = ((officeHours.getSalary() / 176) * officeHours.getTotalHours());
            //tinh Standard_Income
            float standard_Income = officeHours.getOfficeHours() * (officeHours.getSalary() / 176);
            //tinh thue ot_Income
            float BHXH_Ot_Income = ot_Income * BHXH;
            float TNCN_Ot_Income = ot_Income * TNCN;
            float BHTN_Ot_Income = ot_Income * BHTN;
            //tinh thue standard_Income
            float BHXH_Standard_Income = standard_Income * BHXH;
            float TNCN_Standard_Income = standard_Income * TNCN;
            float BHTN_Standard_Income = standard_Income * BHTN;
            //tinh total
            float total_Tax = BHTN_Ot_Income + BHXH_Ot_Income + TNCN_Ot_Income + BHTN_Standard_Income + BHXH_Standard_Income + TNCN_Standard_Income;
            //tinh salary            
            LocalDate localDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            float total_Salary = ot_Income + standard_Income - total_Tax + officeHours.getAllowance();
            PayRoll_DTO payRoll = new PayRoll_DTO(0, login.getUserID(), login.getEmployeeId(), sqlDate,
                    officeHours.getOfficeHours(), officeHours.getOtHours(), ot_Income, standard_Income,
                    BHXH, BHTN, TNCN, officeHours.getAllowance(),
                    total_Salary, officeHours.getSalary(), officeHours.getTotalHours());
            if (payRoll == null) {
                url = ERROR;
            } else {
                request.setAttribute("PAYROLL", payRoll);
                //checkout
                boolean checkInsert = dao.InsertDTB(payRoll);
                if (checkInsert) {
                    url = SUCCESS;
                }
            }
            

        } catch (Exception e) {
            log("Error at PayRollController" + e.toString());
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
