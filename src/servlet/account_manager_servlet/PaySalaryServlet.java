package servlet.account_manager_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.script.*;

import data_access.account_manager_data_access.EmployeeSalaryDataAccess;

import javax.servlet.*;

/**
 * 
 * Servlet implementation class PaySalaryServlet
 */
public class PaySalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaySalaryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { // TODO Auto-generated
	 * method stub response.getWriter().append("Served at: "
	 * ).append(request.getContextPath()); }
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		int id = Integer.parseInt(request.getParameter("hidden_id"));
		String date = request.getParameter("date");
		Double grossSalary = Double.parseDouble(request.getParameter("gross"));
		int queryStatus = EmployeeSalaryDataAccess.paySalary(id, date, grossSalary);
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Salary paid successfully.');");
		       out.println("window.location='"+path+"/JSP/account_manager/salary.jsp'");
		       out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Operation failed.');");
		       out.println("window.location='"+path+"/JSP/account_manager/salary.jsp'");
		       out.println("</script>");
		}
	}

}