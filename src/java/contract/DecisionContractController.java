/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package contract;

import candidate.CandidateDTO;
import candidate.CandidateDAO;
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
 * @author flami 
 * This servlet class use to handle approve or reject temporary
 * contract by HRM
 */
@WebServlet(name = "DecisionContractController", urlPatterns = {"/DecisionContractController"})
public class DecisionContractController extends HttpServlet {

    private final static String ERROR = "/contract/showCandidateContractDetail.jsp";
    private final static String SUCCESS = "/contract/searchContract.jsp";
    private final static String CONTRACT_PRINTING = "contract/printContract.jsp";
    private final static String URL = "main/mainHRM.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        boolean check = true;
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            String description = request.getParameter("description");
            String candidateID = request.getParameter("candidateID");
            String contractID = request.getParameter("contractID");

            ContractDAO dao = new ContractDAO();
            CandidateDTO candidate = dao.getACandidate(candidateID);
            TemporaryContractDTO tempContract = dao.getTemporaryContract(contractID);

            String roleName;
            HttpSession session = request.getSession();
            User_Login_DTO userLogin = (User_Login_DTO) session.getAttribute("USER_LOGIN");
            roleName = userLogin.getRoleName();

            if (roleName.equals("HRM")) {
                if (action.equals("Reject Contract")) {
                    if (description.isEmpty() || description == null) {
                        request.setAttribute("ERROR_MESSAGE",
                                "Need to fill in Reason to reject!");
                        check = false;
                    } else {
                        tempContract.setStatus("REJECTED");
                        tempContract.setReason(description);
                    }
                } else if (action.equals("Approve Contract")) {
                    tempContract.setStatus("APPROVED");
                    tempContract.setReason(description);
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
