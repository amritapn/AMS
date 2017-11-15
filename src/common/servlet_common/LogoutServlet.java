package common.servlet_common;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatch;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		try {
			HttpSession session = request.getSession(false);
			session.removeAttribute("user_id");
			session.removeAttribute("user_name");
			session.invalidate();
			out.println("<script language='javascript'>");
		       out.println("window.alert('You are successfully logged out!');");
		       out.println("window.location='"+path+"/JSP/commonjsp/Login.jsp'");
		       out.println("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}