package servlet.admin_servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.*;

import data_access.admin_data_access.StudentDataAccess;
import pojo.admin_pojo.Student;

public class AddStudentServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		Student student = new Student();
		student.setName(request.getParameter("name"));
		student.setGender(request.getParameter("gender"));
		student.setDob(request.getParameter("dob"));
		student.setPhone(request.getParameter("mobile"));
		student.setEmail(request.getParameter("mail"));
		student.setPlot(request.getParameter("plot"));
		student.setCity(request.getParameter("city"));
		student.setState(request.getParameter("state"));
		student.setPin(request.getParameter("pin1"));
		student.setDoj(request.getParameter("doj"));
		student.setCourse(request.getParameter("course"));
		student.setPaymentType(request.getParameter("payment"));
		student.setInstallmentAmount(Double.parseDouble(request.getParameter("install")));
		student.setPaid(Double.parseDouble(request.getParameter("install")));
		student.setInstallmentDuration(request.getParameter("duration"));
		double amount = Double.parseDouble(request.getParameter("amount"));
		String date = request.getParameter("currentdate");
		String paytype = student.getPaymentType();
		if (paytype.equals("Full")) {
			student.setPaid(amount);
			student.setPaymentPending(0.0);
		} else {
			double paid = student.getPaid();
			double pending = amount - paid;
			student.setPaymentPending(pending);
		}
		int queryStatus = StudentDataAccess.add(student, date);
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Student added successfully.');");
		       out.println("window.location='"+path+"/JSP/admin/StudentDataEntry.jsp'");
		       out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
			       out.println("window.alert('Student registration failed.');");
			       out.println("window.location='"+path+"/JSP/admin/StudentDataEntry.jsp'");
			       out.println("</script>");
		}
	}
}