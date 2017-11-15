package servlet.admin_servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.*;
import data_access.admin_data_access.StudentDataAccess;
import pojo.admin_pojo.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditStudentServlet
 */
public class EditStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		Student student = new Student();
		student.setId(Integer.parseInt(request.getParameter("id")));
		student.setName(request.getParameter("name"));
		student.setGender(request.getParameter("gender"));
		student.setDob(request.getParameter("dob"));
		student.setPhone(request.getParameter("mobile"));
		student.setEmail(request.getParameter("mail"));
		student.setPlot(request.getParameter("plot"));
		student.setCity(request.getParameter("city"));
		student.setState(request.getParameter("state"));
		student.setPin(request.getParameter("pin1"));
		student.setCourse(request.getParameter("course"));
		student.setDoj(request.getParameter("doj"));
		student.setStatus(request.getParameter("status"));
		int queryStatus = StudentDataAccess.edit(student);
		response.setContentType("text/html");
		if(queryStatus > 0) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Record updated successfully.');");
		       out.println("window.location='"+path+"/JSP/admin/ViewStudent.jsp?show=all'");
		       out.println("</script>");
			
		}else {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Operation failed.');");
		       out.println("window.location='"+path+"/JSP/admin/ViewStudent.jsp?show=all'");
		       out.println("</script>");
		}
		
	}

}