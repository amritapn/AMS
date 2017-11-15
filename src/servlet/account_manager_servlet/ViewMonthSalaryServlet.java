package servlet.account_manager_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import data_access.account_manager_data_access.EmployeeSalaryDataAccess;

/**
 * Servlet implementation class ViewMonthSalaryServlet
 */
public class ViewMonthSalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewMonthSalaryServlet() {
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
		String path = (String) request.getContextPath();
		int id = Integer.parseInt(request.getParameter("hidden_id"));
		int year = Integer.parseInt(request.getParameter("year"));
		String month = request.getParameter("month");
		double grossSalary = EmployeeSalaryDataAccess.viewMonthWise(id, year, month);
		if (grossSalary > 0.0) {
			out.println("<script language='javascript'>");
			out.println("window.alert('Salary disbursed to emp_ID : " + id + " in the month of " + month + " is Rs. "
					+ grossSalary + ".');");
			out.println("window.location='" + path + "/JSP/account_manager/salary.jsp'");
			out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
			out.println("window.alert('No salary has been disbursed to emp_ID : " + id + " in the month of " + month
					+ " is Rs. " + grossSalary + ".');");
			out.println("window.location='" + path + "/JSP/account_manager/salary.jsp'");
			out.println("</script>");
		}
	}

}