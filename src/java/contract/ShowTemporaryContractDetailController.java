/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package contract;

import candidate.CandidateDTO;
import candidate.CandidateDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import userlogin.User_Login_DTO;

/**
 *
 * @author flami This servlet class use to show information about temporary
 * contract of candidate
 */
@WebServlet(name = "ShowTemporaryContractDetailController", urlPatterns = {"/ShowTemporaryContractDetailController"})
public class ShowTemporaryContractDetailController extends HttpServlet {

    private final static String ERROR = "/contract/searchContract.jsp";
    private final static String SUCCESS = "/contract/showCandidateContractDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;
        String URL = ERROR;
        try {
            String search = request.getParameter("search");
            String candidateID = request.getParameter("candidateID");
            String contractID = request.getParameter("contractID");
            String statusContract = (String) request.getAttribute("statusContract");
            ContractDAO dao = new ContractDAO();
            //get candidate and temporary contract
            CandidateDTO candidate = dao.getACandidate(candidateID);
            TemporaryContractDTO tempContract = dao.getTemporaryContract(contractID);

            if (candidate != null && tempContract != null) {
                //set attribute for candidate and temporary contract of this candidate
                request.setAttribute("CANDIDATE", candidate);
                request.setAttribute("TEMPORARY_CONTRACT", tempContract);
                url = SUCCESS;
            }
            request.setAttribute("search", search);
            request.setAttribute("STATUS_CONTRACT", search);

            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            if (userLogin.getRoleName().equalsIgnoreCase("HRS")) {
                URL = "main/mainHRS.jsp";
            } else if (userLogin.getRoleName().equalsIgnoreCase("HRM")) {
                URL = "main/mainHRM.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
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