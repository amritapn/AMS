package common.servlet_common;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;

import common.pojo_common.PasswordBean;
import data_access.system_admin_data_access.SystemDataAccess;

public class PasswordServlet extends HttpServlet {
	PrintWriter out;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String path = (String) request.getContextPath();
		try {
			out = response.getWriter();
			String oldPass = request.getParameter("oldPass");
			String newPass = request.getParameter("newPass");
			int id = Integer.parseInt(request.getParameter("id"));

			PasswordBean pass = new PasswordBean();
			pass.setOldPass(oldPass);
			pass.setNewPass(newPass);
			pass.setId(id);

			SystemDataAccess check = new SystemDataAccess();
			String returnPass = check.checkPass(pass);
			// out.println(returnPass);
			if (oldPass.equals(returnPass)) {
				out.println("<script language='javascript'>");
				out.println("window.alert('Password updated successfully.');");
				out.println("window.location='" + path + "/JSP/commonjsp/Login.jsp'");
				out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
				out.println("window.alert('Sorry!!Try it Again..');");
				out.println("window.location='" + path + "/JSP/commonjsp/hangePassword.jsp'");
				out.println("</script>");

			}
		} catch (Exception e) {
			out.println(e.getMessage());
		}
	}
}