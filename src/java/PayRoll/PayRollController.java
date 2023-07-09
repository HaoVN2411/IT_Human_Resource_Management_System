/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//HRM can join
package PayRoll;

import Employee_Info.Employee_Info_DAO;
import Employee_Info.Employee_Info_DTO;
import User_Login_Controller.User_Login_DTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.sql.SQLException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
            LocalDate localDate = LocalDate.now();
            java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            PayRoll_DAO dao = new PayRoll_DAO();
            HttpSession session = request.getSession();
            List<String> list = dao.getEmployeeID(month - 1, year);
            List<String> listIdPayroll = dao.getEmployeeIDPayroll(month, year);
            if (!list.isEmpty()) {
                for (String employeeID : list) {
                    if (listIdPayroll.size() == 0) {
                        List<Date> dateLeave = dao.getDateLeave(employeeID, month - 1, year);
                        List<Date> endHour = dao.getEndHour(employeeID, month - 1, year);
                        PayRoll_DTO totalHourPayRoll = dao.getTotalHour(employeeID, month - 1, year);
                        final float BHXH = (float) 0.045;
                        final float TNCN = (float) 0.05;
                        final float BHTN = (float) 0.02;
                        //Calculate OT_income
                        float ot_Income = ((totalHourPayRoll.getSalary() / 176) * totalHourPayRoll.getTotalHours());
                        //Calculate Standard_Income
                        float standard_Income = totalHourPayRoll.getOfficeHours() * (totalHourPayRoll.getSalary() / 176);
                        //Calculate tax OT_income
                        float BHXH_Ot_Income = ot_Income * BHXH;
                        float TNCN_Ot_Income = ot_Income * TNCN;
                        float BHTN_Ot_Income = ot_Income * BHTN;
                        //Calculate tax Standard_Income
                        float BHXH_Standard_Income = standard_Income * BHXH;
                        float TNCN_Standard_Income = standard_Income * TNCN;
                        float BHTN_Standard_Income = standard_Income * BHTN;
                        //Calculate Total
                        float total_Tax = BHTN_Ot_Income + BHXH_Ot_Income + TNCN_Ot_Income + BHTN_Standard_Income + BHXH_Standard_Income + TNCN_Standard_Income;
                        //Calculate Salary 
                        int countDateLeave = 0;
                        for (Date leaveDate : dateLeave) {
                            boolean foundValue = false;
                            for (Date endTime : endHour) {
                                if (endTime.equals(leaveDate)) {
                                    foundValue = true;
                                    break;
                                }
                            }
                            if (!foundValue) {
                                countDateLeave++;
                            }
                        }
                        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                        DecimalFormat decimalFormat = new DecimalFormat("#0.00", symbols);
                        decimalFormat.setParseBigDecimal(true);
                        float total_Salary = ot_Income + standard_Income - total_Tax + totalHourPayRoll.getAllowance() + countDateLeave * 8 * (totalHourPayRoll.getSalary() / 176);
                        total_Salary = decimalFormat.parse(decimalFormat.format(total_Salary)).floatValue();
                        //BigDecimal a = new BigDecimal(decimalFormat.format(total_Salary)).setScale(2, RoundingMode.HALF_UP);
                        PayRoll_DTO payRoll = new PayRoll_DTO(0, null, employeeID, sqlDate,
                                totalHourPayRoll.getOfficeHours(), totalHourPayRoll.getOtHours(), ot_Income, standard_Income,
                                BHXH, BHTN, TNCN, totalHourPayRoll.getAllowance(),
                                total_Salary , 0, 0);
                        boolean checkInsert = dao.InsertDTB(payRoll);
                    } else {
                        url = ERROR;
                    }
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
