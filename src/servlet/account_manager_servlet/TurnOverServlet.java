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
 * Servlet implementation class TurnOverServlet
 */
public class TurnOverServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TurnOverServlet() {
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
		int year = Integer.parseInt(request.getParameter("year"));
		int tax = Integer.parseInt(request.getParameter("tax"));
		double totalIncome = ExpenseDataAccess.getTotalIncome(year);
		double turnOver = ExpenseDataAccess.getTurnOver(year, tax);
		if (totalIncome > 0.0 || turnOver > 0.0) {
			if (totalIncome == 0.0) {
				out.println("<script language='javascript'>");
				out.println("window.alert('No income has been made in this year.');");
				out.println("window.location='" + path + "/JSP/account_manager/TurnOver.jsp'");
				out.println("</script>");
			}

			else if (turnOver == 0.0) {
				out.println("<script language='javascript'>");
				out.println(
						"window.alert('Total income is " + totalIncome + " and turn over is " + turnOver + " . ');");
				out.println("window.location='" + path + "/JSP/account_manager/TurnOver.jsp'");
				out.println("</script>");
			}

			else if (totalIncome > 0.0 && turnOver > 0.0) {
				out.println("<script language='javascript'>");
				out.println(
						"window.alert('Total income is " + totalIncome + " and turn over is " + turnOver + " . ');");
				out.println("window.location='" + path + "/JSP/account_manager/TurnOver.jsp'");
				out.println("</script>");
			}

			else {
				out.println("<script language='javascript'>");
				out.println("window.alert('Total income is less than total expense this year.');");
				out.println("window.location='" + path + "/JSP/account_manager/TurnOver.jsp'");
				out.println("</script>");
			}
		} else {
			out.println("<script language='javascript'>");
			out.println("window.alert('No income has been made in this year.');");
			out.println("window.location='" + path + "/JSP/account_manager/TurnOver.jsp'");
			out.println("</script>");
		}
	}

}