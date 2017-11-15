package servlet.hr_servlet;

import java.io.IOException;
import java.text.*;
import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.common_data_access.LeaveDataAccess;

/**
 * Servlet implementation class ApproveLeaveServlet
 */
public class ApproveLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApproveLeaveServlet() {
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
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String startDate = request.getParameter("start");
			String endDate = request.getParameter("end");
			int duration = Integer.parseInt(request.getParameter("duration"));
			int queryStatus = LeaveDataAccess.calculateLeave(id, type, startDate, endDate, duration);
			if (queryStatus > 0) {
				RequestDispatcher dispatch = request.getRequestDispatcher("/JSP/hr/PendingLeaves.jsp");
				dispatch.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}