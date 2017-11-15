package servlet.admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;

//import javax.swing.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import data_access.admin_data_access.CourseDataAccess;
import pojo.admin_pojo.Course;

/**
 * Servlet implementation class AddCourseServlet
 */
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCourseServlet() {
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
		// String path = (String)request.getContextPath();
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		Course course = new Course();
		course.setName(request.getParameter("cname"));
		course.setDuration(request.getParameter("duration"));
		course.setFees(Double.parseDouble(request.getParameter("fees")));
		int queryStatus = 0;
		queryStatus = CourseDataAccess.addCourse(course);
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Course added successfully.');");
		       out.println("window.location='"+path+"/JSP/admin/CourseDataEntry.jsp'");
		       out.println("</script>");
			} else {
				out.println("<script language='javascript'>");
			       out.println("window.alert('Operation failed');");
			       out.println("window.location='"+path+"/JSP/admin/CourseDataEntry.jsp'");
			       out.println("</script>");
		}
	}

}