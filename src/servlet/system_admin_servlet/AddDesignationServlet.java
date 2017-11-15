package servlet.system_admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import data_access.system_admin_data_access.DesignationDataAccess;
import pojo.system_admin_pojo.Designation;

/**
 * Servlet implementation class AddDesignationServlet
 */
public class AddDesignationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDesignationServlet() {
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
		Designation designation = new Designation();
		designation.setDesignation(request.getParameter("designation"));
		designation.setRole(request.getParameter("role"));
		boolean present = DesignationDataAccess.isPresent(designation.getDesignation());
		int queryStatus = 0;
		if (present == false) {
			queryStatus = DesignationDataAccess.addDesignation(designation);

		} else {
			queryStatus = DesignationDataAccess.updateDesignation(designation);
		}
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
			out.println("window.alert('List of valid designations updated successfully.');");
			out.println("window.location='" + path + "/JSP/system_admin/DesignationEntry.jsp'");
			out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
			out.println("window.alert('Operation failed.');");
			out.println("window.location='" + path + "/JSP/system_admin/DesignationEntry.jsp'");
			out.println("</script>");
		}
	}

}