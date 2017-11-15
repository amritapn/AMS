package servlet.account_manager_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import common.common_data_access.ExpenseDataAccess;

/**
 * Servlet implementation class ShowTotalExpense
 */
public class ShowTotalExpense extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowTotalExpense() {
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
		int day = Integer.parseInt(request.getParameter("day"));
		String month = request.getParameter("month");
		int year = Integer.parseInt(request.getParameter("year"));

		double totalExpense = ExpenseDataAccess.getTotalExpense(day, month, year);
		if (totalExpense > 0.0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Total expense is "+totalExpense+");'");
		       out.println("window.location='/TotalExpense.jsp'");
		       out.println("</script>");
			
		} else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('No expense has been made on this date.'");
		       out.println("window.location='/TotalExpense.jsp'");
		       out.println("</script>");
		}

		}

}