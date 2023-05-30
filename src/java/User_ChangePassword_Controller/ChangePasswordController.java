/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User_ChangePassword_Controller;

import User_Login_Controller.UserError;
import User_Login_Controller.User_Login_DAO;
import User_Login_Controller.User_Login_DTO;
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
 * @author CAO-KIEN-QUOC
 */
@WebServlet(name = "ChangePasswordController", urlPatterns = {"/ChangePasswordController"})
public class ChangePasswordController extends HttpServlet {

    private static final String ERROR = "change.jsp";
    private static final String SUCCESS = "change.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        UserError userError = new UserError();
        try {
            String userID = request.getParameter("userID");
            String oldPassword = request.getParameter("oldpassword");
            String newPassword = request.getParameter("newpassword");
            User_Login_DAO dao = new User_Login_DAO();           
            HttpSession session = request.getSession();
            User_Login_DTO loginUser = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            boolean checkValidation = true;
                if (!loginUser.getPassword().equals(oldPassword)) {
                      checkValidation = false;
                      userError.setPasswordError("Old Pass Word is incorrect!!!"); 
                }if(newPassword.equals(oldPassword)){
                    checkValidation = false;
                    userError.setConfirmError("The new password cannot be the same as the old password!!");
                }
                if(newPassword.length() < 8){
                    checkValidation = false;
                    userError.setNewPasswordError("Password must be at least 8 characters");
                }
                if(loginUser.getUserID().equals(userID)){
                    loginUser.setPassword(newPassword);
                    session.setAttribute("USER_LOGIN", loginUser);
                }
                if(checkValidation){
                    User_Login_DTO user = new User_Login_DTO(userID, newPassword, true, "", "");
                    boolean checkUpdate = dao.change(user);
                    if(checkUpdate){
                        url = SUCCESS;   
                        request.setAttribute("SUCCESS", "change password succesfully!!");
                    } else {
                        request.setAttribute("ERROR", "Can't not change password");
                    }
                } else {
                    request.setAttribute("USER_ERROR", userError);
                }
                
            }catch (Exception e) {
            log("error at ChangPasswordController: " + e.toString());
        }
            finally {
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
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
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
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }