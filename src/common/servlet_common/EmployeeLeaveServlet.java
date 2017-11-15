package common.servlet_common;

import java.io.IOException;
import java.util.*;
import java.text.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;

import common.common_data_access.LeaveDataAccess;

/**
 * Servlet implementation class EmployeeLeaveServlet
 */
public class EmployeeLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeLeaveServlet() {
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

		try {
			int id = Integer.parseInt(request.getParameter("id"));
			String type = request.getParameter("type");
			String startDate = request.getParameter("startdate");
			String endDate = request.getParameter("enddate");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date start = format.parse(startDate);
			java.util.Date end = format.parse(endDate);
			int duration = (int) (((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24))) + 1;
			JOptionPane.showMessageDialog(null, "duration: " + duration);
			int queryStatus = LeaveDataAccess.calculateLeave(id, type, startDate, endDate, duration);
			RequestDispatcher dispatch = request.getRequestDispatcher("ViewEmployee.jsp?show=all");
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}