package common.servlet_common;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;

import common.pojo_common.ForgotPasswordBean;
import data_access.system_admin_data_access.SystemDataAccess;

public class ForgotPasswordServlet extends HttpServlet {
	PrintWriter out;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		try {
			PrintWriter out = response.getWriter();
			String path = (String) request.getContextPath();
			out = response.getWriter();
			String email = request.getParameter("email");
			long phone = Long.parseLong(request.getParameter("phone"));
			String newPass = request.getParameter("newPass");
			String rePass = request.getParameter("rePass");

			ForgotPasswordBean bean = new ForgotPasswordBean();
			bean.setEmail(email);
			bean.setPhone(phone);
			bean.setNewPass(newPass);
			bean.setRePass(rePass);

			SystemDataAccess checkPass = new SystemDataAccess();
			String check = checkPass.forgotPass(bean);
			out.println(check);

			if (rePass.equals(check)) {
				out.println("<script language='javascript'>");
				out.println("window.alert('Password updated successfully.');");
				out.println("window.location='" + path + "/JSP/commonjsp/Login.jsp'");
				out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
				out.println("window.alert('Sorry try it again');");
				out.println("window.location='" + path + "/JSP/commonjsp/ForgotPassword.jsp'");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}