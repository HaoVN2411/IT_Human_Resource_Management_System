/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_Login_Controller;

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
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

    private static final String ERROR = "change_password.jsp";
    private static final String SUCCESS = "change_password.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String newConfirm = request.getParameter("newConfirm");
//            String oldPassword = "123456789";
//            String newPassword = "12345678";
//            String newConfirm = "12345678";
            User_Login_DAO dao = new User_Login_DAO();
            HttpSession session = request.getSession();
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            boolean checkValidation = true;
            if (!loginUser.getPassword().equals(oldPassword)) {
                checkValidation = false;
                userError.setPasswordError("The current password is not correct.");
            } else if (newPassword.equals(oldPassword)) {
                checkValidation = false;
                userError.setPasswordError("A new password cannot be the current password.");
            } else if (newPassword.length() < 8) {
                checkValidation = false;
                userError.setPasswordError("Password must be at least 8 characters");
            } else if (!newPassword.equals(newConfirm)) {
                checkValidation = false;
                userError.setPasswordError("wrong confirm password");
            }
            if (checkValidation) {
                User_Login_DTO user = new User_Login_DTO(loginUser.getUserID(), newPassword, true, "", "");
                boolean checkUpdate = dao.changePassword(user);
                if (checkUpdate) {
                    loginUser.setPassword(newPassword);
                    session.setAttribute("USER_LOGIN", loginUser);
                    request.setAttribute("MESSAGE", "Change password succesfully");
                    url = SUCCESS;
                    
                } else {
                    request.setAttribute("MESSAGE", "Can't not change password");
                }
            } else {
                request.setAttribute("USER_ERROR", userError);
            }

        } catch (Exception e) {
            log("error at ChangPasswordController: " + e.toString());
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
