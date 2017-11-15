package servlet.hr_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;

import common.common_data_access.LeaveDataAccess;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RejectLeaveServlet
 */
public class RejectLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RejectLeaveServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			PrintWriter out = response.getWriter();
			String path = (String) request.getContextPath();
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String startDate = request.getParameter("start");
			String endDate = request.getParameter("end");
			int duration = Integer.parseInt(request.getParameter("duration"));
			int queryStatus = LeaveDataAccess.rejectLeave(id, type, startDate, endDate, duration);
			if (queryStatus > 0) {
				out.println("<script language='javascript'>");
				out.println("window.alert('Leave application rejected successfully.');");
				out.println("window.location='" + path + "/JSP/hr/PendingLeaves.jsp'");
				out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
				out.println("window.alert('Operation failed.');");
				out.println("window.location='" + path + "/JSP/hr/PendingLeaves.jsp'");
				out.println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}