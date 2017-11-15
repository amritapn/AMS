package common.servlet_common;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import common.common_data_access.LeaveDataAccess;

/**
 * Servlet implementation class TakeLeaveServlet
 */
public class TakeLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TakeLeaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String path = (String) request.getContextPath();
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String startDate = request.getParameter("startdate");
			String endDate = request.getParameter("enddate");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date start = format.parse(startDate);
			java.util.Date end = format.parse(endDate);
			int duration = (int) (((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24))) + 1;
			int answer = JOptionPane.showConfirmDialog(null, "Do you want to apply leave for " + duration + " days?");
			if (answer == JOptionPane.YES_OPTION) {
				int queryStatus = LeaveDataAccess.addToWaitingLeaves(id, type, startDate, endDate, duration);
				if (queryStatus > 0) {
					out.println("<script language='javascript'>");
					out.println(
							"alert('Leave applied successfully. You will be notified when your leave is approved.')");
					out.println("window.location='" + path + "/JSP/commonjsp/HomePage.jsp'");
					out.println("</script>");

				}
			} else {
				RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/commonjsp/LeaveApply.jsp?id=" + id);
				dispatch.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}