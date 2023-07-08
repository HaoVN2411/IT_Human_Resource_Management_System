package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import Candidate.CandidateDTO;
import Candidate.CandidateError;

public final class createCandidate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>CANDIDATE FORM</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/createCandidate.css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");

            CandidateError candidateError = (CandidateError) request.getAttribute("CANDIDATE_ERROR");
            if (candidateError == null) {
                candidateError = new CandidateError();
            }

            CandidateDTO candidate = (CandidateDTO) request.getAttribute("CANDIDATE");
            if (candidate == null) {
                candidate = new CandidateDTO();
            }
        
      out.write("\r\n");
      out.write("        <div class=\"container\">  \r\n");
      out.write("            <form id=\"contact\" action=\"MainController\"  method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("                <h3 style=\"text-align: center;\">CANDIDATE</h3>\r\n");
      out.write("                <h4>Contact us today, and get reply with in 24 hours!</h4>\r\n");
      out.write("\r\n");
      out.write("                <div class=\"content\">\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"text\">Full Name</label>\r\n");
      out.write("                        <input placeholder=\"Jony Dang\" type=\"text\" name=\"fullName\" value=\"");
      out.print(candidate.getFullName());
      out.write("\" tabindex=\"1\" required autofocus>\r\n");
      out.write("                        <div class=\"errorMessage\">  \r\n");
      out.write("                            ");
      out.print( candidateError.getFullNameError());
      out.write("                       \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"gender\">Gender</label>\r\n");
      out.write("                        <select name=\"gender\" class=\"gender\" tabindex=\"2\" value=\"");
      out.print(candidate.getGender());
      out.write("\">\r\n");
      out.write("                            <option value=\"male\" ");
      out.print( candidate.getGender() != null
                                    && candidate.getGender().equals("male")
                                    ? "selected" : "");
      out.write(">Male</option>\r\n");
      out.write("                            <option value=\"female\" ");
      out.print( candidate.getGender() != null
                                    && candidate.getGender().equals("female")
                                    ? "selected" : "");
      out.write(">Female</option>\r\n");
      out.write("                            <option value=\"other\" ");
      out.print( candidate.getGender() != null
                                    && candidate.getGender().equals("other")
                                    ? "selected" : "");
      out.write(">Other</option>\r\n");
      out.write("                        </select>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"date\">Date Of Birth</label>\r\n");
      out.write("                        <input type=\"date\" id=\"birthdate\" name=\"dateOfBirth\" \r\n");
      out.write("                               value=\"");
      out.print( candidate.getDateOfBrith() != null
                                   ? candidate.getDateOfBrith().toString() : "");
      out.write("\"\r\n");
      out.write("                               min=\"1963-01-01\" max=\"2005-01-01\" tabindex=\"3\" required>\r\n");
      out.write("                    </div>\r\n");
      out.write("\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"phoneNumber\">Phone Number</label>\r\n");
      out.write("                        <input placeholder=\"0706600127\" type=\"tel\" name=\"phoneNumber\"\r\n");
      out.write("                               value=\"");
      out.print(candidate.getPhoneNumber());
      out.write("\" tabindex=\"4\" required>\r\n");
      out.write("                        <div class=\"errorMessage\">  \r\n");
      out.write("                            ");
      out.print( candidateError.getPhoneNumberError());
      out.write("                       \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"email\">Email</label>\r\n");
      out.write("                        <input placeholder=\"abc@xyz.com\" type=\"email\" name=\"email\" \r\n");
      out.write("                               value=\"");
      out.print(candidate.getEmail());
      out.write("\" tabindex=\"5\" required>\r\n");
      out.write("                        <div class=\"errorMessage\">  \r\n");
      out.write("                            ");
      out.print( candidateError.getEmailError());
      out.write("                       \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"address\">Address</label>\r\n");
      out.write("                        <input placeholder=\"Phường Tân Phú, Quận 9, Thành phố Hồ Chí Minh, Vietnam\"\r\n");
      out.write("                               type=\"text\" name=\"address\" value=\"");
      out.print(candidate.getAddress());
      out.write("\" \r\n");
      out.write("                               tabindex=\"6\" required>\r\n");
      out.write("                        <div class=\"errorMessage\">  \r\n");
      out.write("                            ");
      out.print( candidateError.getAddressError());
      out.write("                       \r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-group\">\r\n");
      out.write("                        <label for=\"humanId\">Human Id</label>\r\n");
      out.write("                        <input placeholder=\"xxxx xxxx xxxx\" type=\"text\" name=\"humanId\" \r\n");
      out.write("                               value=\"");
      out.print(candidate.getHumanId());
      out.write("\" tabindex=\"7\" required>\r\n");
      out.write("                        <div class=\"errorMessage\">  \r\n");
      out.write("                            ");
      out.print( candidateError.getHumanIdEror());
      out.write("                       \r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <div class=\"input-group\">\r\n");
      out.write("                            <label for=\"nationality\">Nationality</label>\r\n");
      out.write("                            <input placeholder=\"Viet Nam\" type=\"text\" name=\"nationality\"\r\n");
      out.write("                                   value=\"");
      out.print(candidate.getNationality());
      out.write("\" tabindex=\"8\" required>\r\n");
      out.write("                            <div class=\"errorMessage\">  \r\n");
      out.write("                                ");
      out.print( candidateError.getNationalityError());
      out.write("                       \r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"input-group\">\r\n");
      out.write("                            <label for=\"notation\">Notation</label>\r\n");
      out.write("                            <input placeholder=\"Type your Message Here....\" type=\"text\" \r\n");
      out.write("                                   name=\"notation\" value=\"");
      out.print(candidate.getNotation());
      out.write("\" \r\n");
      out.write("                                   tabindex=\"9\" ></input>\r\n");
      out.write("                            <div class=\"errorMessage\">  \r\n");
      out.write("                                ");
      out.print( candidateError.getNotationError());
      out.write("                       \r\n");
      out.write("                            </div>                    \r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"input-group\">\r\n");
      out.write("                            <label for=\"image\">Image</label>\r\n");
      out.write("                            <input type=\"file\" name=\"image\" tabindex=\"10\" required>  \r\n");
      out.write("                            <div class=\"errorMessage\">  \r\n");
      out.write("                                ");
      out.print( candidateError.getImageError());
      out.write("                       \r\n");
      out.write("                            </div>\r\n");
      out.write("                        </div>\r\n");
      out.write("\r\n");
      out.write("                        <input type=\"reset\" id=\"contact-submit\">\r\n");
      out.write("                        <input name=\"action\" type=\"submit\" value=\"Create Candidate\" id=\"contact-submit\" >\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"errorMessage\">  \r\n");
      out.write("                        ");
      out.print( candidateError.getMessageError());
      out.write("                       \r\n");
      out.write("                    </div>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
