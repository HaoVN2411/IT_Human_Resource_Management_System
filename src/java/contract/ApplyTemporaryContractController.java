/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package contract;

import candidate.CandidateDTO;
import candidate.CandidateDAO;
import candidate.CandidateError;
import candidate.Helper;
import static candidate.Helper.getImageFileName;
import candidate.ValidationInput;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author flami This servlet class apply contract which is scaned by HRS and
 * add new employee and contract
 */
@WebServlet(name = "ApplyTemporaryContractController", urlPatterns = {"/ApplyTemporaryContractController"})
public class ApplyTemporaryContractController extends HttpServlet {

    private final static String ERROR = "/contract/showCandidateContractDetail.jsp";
    private final static String SUCCESS = "SearchContractController";
    private final static String SAVE_DIR = "imageContract";
    private final static String EMPLOYEE_CONTRACT_ID_FORMAT = "EC1111";
    private final static String STAFF_ID_FORMAT = "SS1111";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "main/mainHRS.jsp";
        String url = ERROR;
        try {
            String statusContract = "";
            String messageError;
            boolean check = true;
            ValidationInput errorChecking = new ValidationInput();

            Part file = request.getPart("fileContract");
            String candidateID = (String) request.getParameter("candidateID");
            String contractID = (String) request.getParameter("contractID");

            CandidateDAO daoCand = new CandidateDAO();
            CandidateDTO candidate = daoCand.getACandidate(candidateID);//get a candidate from database

            ContractDAO daoCont = new ContractDAO();
            //get a temporary contract of this candidate from database
            TemporaryContractDTO tempCont = daoCont.getTemporaryContract(contractID);
            statusContract = tempCont.getStatus();

            if (tempCont.getStatus().equals("APPROVED")) {
                //get new staff ID: SS****, *:degit
                String staffID = daoCont.getNewStaffID(STAFF_ID_FORMAT);
                //get new Employee Contract ID: EC****, *:degit
                String employeeContractID = daoCont.getNewEmployeeContractID(
                        EMPLOYEE_CONTRACT_ID_FORMAT);
                //get a character used to separate directories
                String seperateFile = File.separator;
                //get relative path
                String path = getServletContext().getRealPath("/");
                String webPath = new File(path).getParentFile().getParentFile().getPath()
                        + seperateFile + "web"; // relative path to web folder
                String savePath = Helper.getSavePathApplyContract(webPath, SAVE_DIR, staffID); //folder contain img contract
                String fileName = "";
                String extension = "";
                int count = 0;
                //write contract img to folder
                for (Part part : request.getParts()) {
                    fileName = part.getSubmittedFileName();
                    if (fileName != null) {
                        extension = fileName.substring(fileName.lastIndexOf("."));
                        if (extension.equals(".jpg") || extension.equals(".png")) {
                            String fileNameImg = String.valueOf(++count);
                            String filePath = savePath + seperateFile + "image_" + fileNameImg + ".jpg";
                            part.write(filePath);
                        }
                    }

                }
                //Set New Employee Contract
                candidate.setId(staffID);
                tempCont.setContractID(employeeContractID);
                tempCont.setCandidateID(staffID);
                tempCont.setSizeImage(count);
                tempCont.setPathImage(SAVE_DIR + seperateFile + staffID);
                //Insert new employee and contract 
                if (daoCont.insertEmployee(candidate)
                        && daoCont.insertEmployeeContract(tempCont)) {
                    //insert information for UserLogin in database
                    daoCont.insertStaffToUserLogin(candidate);
                    // reset candidate 
                    candidate.setIsActive(false);
                    candidate.setId(candidateID);
                    daoCand.updateCandidate(candidate);
                    // reset to temporary Contract
                    tempCont.setContractID(contractID);
                    tempCont.setCandidateID(candidateID);
                    daoCont.updateTemporaryContract(tempCont);
                    url = SUCCESS;
                    URL = SUCCESS;
                }
            } else {
                request.setAttribute("URL", url);
                request.setAttribute("ERROR_MESSAGE", "Contract is " + statusContract
                        + "! Do not apply contract!");
                request.setAttribute("CANDIDATE", candidate);
                request.setAttribute("TEMPORARY_CONTRACT", tempCont);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
