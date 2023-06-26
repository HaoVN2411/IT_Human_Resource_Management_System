/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Contract;

import Candidate.Candidate;
import Candidate.CandidateDAO;
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
 * @author flami
 * This servlet class use to handle approve or reject temporary contract by HRM
 */
@WebServlet(name = "DecisionContractController", urlPatterns = {"/DecisionContractController"})
public class DecisionContractController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "showCandidateContractDetail.jsp";
    private final static String SUCCESS = "searchContract.jsp";
    private final static String CONTRACT_PRINTING = "printContract.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = true;
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            String discription = request.getParameter("discription");
            String candidateID = request.getParameter("candidateID");
            String contractID = request.getParameter("contractID");

            ContractDAO dao = new ContractDAO();
            Candidate candidate = dao.getACandidate(candidateID);
            TemporaryContract tempContract = dao.getTemporaryContract(contractID);

            String roleName;
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            roleName = userLogin.getRoleName();

            if (roleName.equals("HRM")) {
                if (action.equals("Reject Contract")) {
                    if (discription.isEmpty() || discription == null) {
                        request.setAttribute("ERROR_MESSAGE",
                                "Need to fill in Reason to reject!");
                        check = false;
                    } else {
                        tempContract.setStatus("REJECTED");
                        tempContract.setReason(discription);
                    }
                } else if (action.equals("Approve Contract")) {
                    tempContract.setStatus("APPROVED");
                    tempContract.setReason(discription);
                }

                if (check) {
                    dao.updateTemporaryContract(tempContract);
                    url = SUCCESS;
                } else {
                    request.setAttribute("CANDIDATE", candidate);
                    request.setAttribute("TEMPORARY_CONTRACT", tempContract);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
