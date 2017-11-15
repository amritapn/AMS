package servlet.admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

import common.common_data_access.ExpenseDataAccess;
import common.pojo_common.Expense;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddExpenseServlet
 */
public class AddExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddExpenseServlet() {
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
		Expense expense = new Expense();
		expense = ExpenseDataAccess.storeDate(request.getParameter("date"), expense);
		expense.setType(request.getParameter("expense"));
		expense.setAmount(Double.parseDouble(request.getParameter("amount")));
		int queryStatus = ExpenseDataAccess.addExpense(expense);
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Expense added successfully.');");
		       out.println("window.location='"+path+"/JSP/admin/AddExpense.jsp'");
		       out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
			       out.println("window.alert('Operation failed.');");
			       out.println("window.location='"+path+"/JSP/admin/AddExpense.jsp'");
			       out.println("</script>");
			}
		}

}