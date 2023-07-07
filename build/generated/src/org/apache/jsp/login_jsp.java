package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<!--\r\n");
      out.write("Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license\r\n");
      out.write("Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template\r\n");
      out.write("-->\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <title>Animated Login Form</title>\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/login.css\">\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Poppins:600&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("        <script src=\"https://kit.fontawesome.com/a81368914c.js\"></script>\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <!--        <img class=\"wave\" src=\"../image/wave.svg\">-->\r\n");
      out.write("        <div class=\"container\">\r\n");
      out.write("            <div class=\"img\">\r\n");
      out.write("                <img src=\"imageLogin/1.svg\">\t\t</div>\r\n");
      out.write("            <div class=\"login-content\">\r\n");
      out.write("\r\n");
      out.write("                <form action=\"MainController\" method=\"post\">\r\n");
      out.write("                    <img src=\"imageLogin/avatar.svg\">\r\n");
      out.write("                    <h2 class=\"title\">Welcome</h2>\r\n");
      out.write("                    <div class=\"input-div one\">\r\n");
      out.write("                        <div class=\"i\">\r\n");
      out.write("                            <i class=\"fas fa-user\"></i>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"div\">\r\n");
      out.write("                            <!--                            <h5>Username</h5>-->\r\n");
      out.write("                            <input placeholder=\"Username\" type=\"text\" class=\"input\" name=\"userID\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-div pass\">\r\n");
      out.write("                        <div class=\"i\"> \r\n");
      out.write("                            <i class=\"fas fa-lock\"></i>\r\n");
      out.write("                        </div>\r\n");
      out.write("                        <div class=\"div\">\r\n");
      out.write("                            <!--                            <h5>Password</h5>-->\r\n");
      out.write("                            <input placeholder=\"Password\" type=\"password\" class=\"input\" name=\"password\">\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <a href=\"#\">Forgot Password?</a>\r\n");
      out.write("                    <input type=\"submit\" class=\"btn\" name=\"action\" value=\"Login\">\r\n");
      out.write("                </form>\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <script type=\"text/javascript\" src=\"login.js\"></script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
      out.write("\r\n");
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
