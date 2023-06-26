/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Candidate;

import Contract.TemporaryContract;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author flami
 * this servlet class return all information about candidate
 */
@WebServlet(name = "ShowCandidateDetailController", urlPatterns = {"/ShowCandidateDetailController"})
public class ShowCandidateDetailController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String ERROR = "searchCandidate.jsp";
    private final static String SUCCESS = "showCandidateDetail.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            String candidateID = request.getParameter("candidateID");
            CandidateDAO dao = new CandidateDAO();
            //get a candidate from database
            Candidate candidate = dao.getACandidate(candidateID);
            if (candidate != null) {
                //get temporary contract of this candidate
                TemporaryContract tempContract = dao.getTemporaryContractByCandidateID(candidateID);
                String status = null;
                if(tempContract!=null)
                 status = tempContract.getStatus();
                if (status != null) {
                    if (status.equals("APPROVED")) {
                        request.setAttribute("STATUS_CONTRACT",
                                "Contract of Candidate is approved by HRM!");
                    }
                    if (status.equals("REJECT")) {
                        request.setAttribute("STATUS_CONTRACT",
                                "Contract of Candidate is rejected by HRM!");
                    }
                    if (status.equals("PROCESSING")) {
                        request.setAttribute("STATUS_CONTRACT",
                                "Contract of Candidate is waiting for HRM!");
                    }
                }
                //return candidate and temporary contract to show
                request.setAttribute("CANDIDATE", candidate);
                request.setAttribute("TEMPORARY_CONTRACT", tempContract);
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
