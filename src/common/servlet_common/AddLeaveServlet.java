package common.servlet_common;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import common.common_data_access.LeaveDataAccess;
import common.pojo_common.Leave;

/**
 * Servlet implementation class AddLeaveServlet
 */
public class AddLeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddLeaveServlet() {
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
		Leave leave = new Leave();
		leave.setType(request.getParameter("leave"));
		leave.setDays(Integer.parseInt(request.getParameter("days")));
		boolean present = LeaveDataAccess.isPresent(leave.getType());
		int queryStatus = 0;
		if (present == false) {
			queryStatus = LeaveDataAccess.addLeave(leave);

		} else {
			queryStatus = LeaveDataAccess.updateLeave(leave);
		}
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('List of available leaves updated successfully.');");
		       out.println("window.location='"+path+"/commonjsp/LeaveEntry.jsp'");
		       out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
			       out.println("window.alert('Operation failed.');");
			       out.println("window.location='"+path+"/commonjsp/LeaveEntry.jsp'");
			       out.println("</script>");
			}
		
	}

}