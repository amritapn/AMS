package servlet.admin_servlet;

/*
	*Gets the informations from the page.
	
*/

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import common.common_data_access.DBConnect;
import common.pojo_common.JBean;
import data_access.admin_data_access.StudentSearchResult;

import javax.servlet.*;

public class ViewStudent extends HttpServlet {
	String studentName, studentId;
	String designation;
	String status;
	ArrayList studentList = new ArrayList();
	Connection connect;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String studentId = request.getParameter("id");

		String studentName = request.getParameter("name");
		String studentPending = request.getParameter("Pending");
		String studentCourse = request.getParameter("course");
		String studentStatus = request.getParameter("status");

		// after getting all the values from the form, call the connection to
		// form an connection object

		connect = DBConnect.prepareConn();

		// now call the bean to set the datas.

		JBean jBean = new JBean();

		jBean.setStudentId(studentId);
		jBean.setStudentName(studentName);
		jBean.setStudentPending(studentPending);
		jBean.setStudentCourse(studentCourse);
		jBean.setStudentStatus(studentStatus);

		// Now as the values are already set, we call to the
		// GetSearchResult.java with connection object and
		// the jBean object.

		StudentSearchResult studentSearchResult = new StudentSearchResult();
		studentList = (ArrayList) studentSearchResult.returnStudentList(connect, jBean);

		// out.println(studentList.get(0));

		request.setAttribute("studentList", studentList);
		// set everything as a arraylist

		RequestDispatcher rd = request.getRequestDispatcher("/JSP/admin/ViewStudent.jsp?show=notall");
		rd.forward(request, response);

	}

}