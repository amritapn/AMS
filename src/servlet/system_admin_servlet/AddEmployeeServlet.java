package servlet.system_admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import data_access.system_admin_data_access.EmployeeDataAccess;
import pojo.system_admin_pojo.Employee;

/**
 * Servlet implementation class AddEmployeeServlet
 */
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		Employee employee = new Employee();
		employee.setName(request.getParameter("empname"));
		employee.setGender(request.getParameter("empgender"));
		employee.setDob(request.getParameter("empdob"));
		employee.setDoj(request.getParameter("empdoj"));
		employee.setDesignation(request.getParameter("empdesignation"));
		employee.setPhone(request.getParameter("empphone"));
		employee.setEmail(request.getParameter("empemail"));
		employee.setPlot(request.getParameter("empplot"));
		employee.setCity(request.getParameter("empcity"));
		employee.setState(request.getParameter("empstate"));
		employee.setPin(request.getParameter("emppin"));
		employee.setRole(request.getParameter("role"));
		employee.setPassword(request.getParameter("password"));
		int queryStatus = EmployeeDataAccess.add(employee);
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Employee added successfully.');");
		       out.println("window.location='"+path+"/JSP/system_admin/EmployeeDataEntry.jsp'");
		       out.println("</script>");
			} else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Employee registration failed.');");
		       out.println("window.location='"+path+"/JSP/system_admin/EmployeeDataEntry.jsp'");
		       out.println("</script>");
		}
}
}