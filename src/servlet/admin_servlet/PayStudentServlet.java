package servlet.admin_servlet;

import java.io.*;
import javax.servlet.*;
import javax.swing.*;

import data_access.admin_data_access.StudentDataAccess;

import javax.servlet.http.*;

public class PayStudentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		int id = Integer.parseInt(request.getParameter("id"));
		String date = request.getParameter("currentdate");
		double amount = Double.parseDouble(request.getParameter("amount"));
		int queryStatus = StudentDataAccess.pay(id, amount, date);

		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Amount paid successfully. for the student id " + id+".');");
		       out.println("window.location='"+path+"/JSP/admin/ViewStudent.jsp?show=all'");
		       out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Payment failed successfully. for the student id " + id+".');");
		       out.println("window.location='"+path+"/JSP/admin/ViewStudent.jsp?show=all'");
		       out.println("</script>");
		}
	}

}