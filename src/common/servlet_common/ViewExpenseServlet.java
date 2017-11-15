package common.servlet_common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import common.common_data_access.ExpenseDataAccess;
import common.pojo_common.Expense;

/**
 * Servlet implementation class ViewExpenseServlet
 */
public class ViewExpenseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewExpenseServlet() {
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
		String month = request.getParameter("month");
		int year = Integer.parseInt(request.getParameter("year"));
		List<Expense> expenseList = ExpenseDataAccess.viewAll(month, year);
		if (!(expenseList.isEmpty())) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/commonjsp/ViewExpenseData.jsp");
			request.setAttribute("month", month);
			request.setAttribute("year", year);
			request.setAttribute("list", expenseList);
			dispatch.forward(request, response);
		} else {
			out.println("<script language='javascript'>");
			out.println("window.alert('There is no data to show.');");
			out.println("window.location='" + path + "/JSP/commonjsp/ViewExpense.jsp'");
			out.println("</script>");

		}
	}

}