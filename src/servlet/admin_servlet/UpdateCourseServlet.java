package servlet.admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import data_access.admin_data_access.CourseDataAccess;
import pojo.admin_pojo.Course;

/**
 * Servlet implementation class UpdateCourseServlet
 */
public class UpdateCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateCourseServlet() {
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
		Course course = new Course();
		int i = Integer.parseInt(request.getParameter("count"));
		int queryStatus = 0;
		for (int count = 1; count <= i; count++) {
			course.setName(request.getParameter("name" + count));
			course.setDuration(request.getParameter("duration" + count));
			course.setFees(Double.parseDouble(request.getParameter("fees" + count)));
			queryStatus = CourseDataAccess.updateCourse(course);
		}
		if (queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Course updated successfully.');");
		       out.println("window.location='"+path+"/JSP/admin/ViewCourse.jsp'");
		       out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Updation failed.');");
		       out.println("window.location='"+path+"/JSP/admin/ViewCourse.jsp'");
		       out.println("</script>");
		}
	}

}