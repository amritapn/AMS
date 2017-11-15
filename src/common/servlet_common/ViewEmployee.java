package common.servlet_common;

/*
*Gets the informations from the page.

*/

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;

import common.common_data_access.DBConnect;
import common.common_data_access.GetSearchResult;
import common.pojo_common.JBean;

import javax.servlet.*;

public class ViewEmployee extends HttpServlet {
	String empName, empId;
	String designation;
	String status;
	ArrayList empList = new ArrayList();
	Connection connect;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		PrintWriter out = response.getWriter();

		String searchEmpId = request.getParameter("id");

		String searchEmpName = request.getParameter("name");
		String searchEmpDesignation = request.getParameter("designation");
		String searchEmpStatus = request.getParameter("status");

		// after getting all the values from the form, call the connection to
		// form an connection object

		connect = DBConnect.prepareConn();

		// now call the bean to set the datas.

		JBean jBean = new JBean();

		jBean.setSearchEmpId(searchEmpId);
		jBean.setSearchEmpName(searchEmpName);
		jBean.setSearchEmpDesignation(searchEmpDesignation);
		jBean.setSearchEmpStatus(searchEmpStatus);

		// Now as the values are already set, we call to the
		// GetSearchResult.java with connection object and
		// the jBean object.

		GetSearchResult getSearchResult = new GetSearchResult();

		empList = (ArrayList) getSearchResult.returnEmpList(connect, jBean);

		request.setAttribute("empList", empList);
		// set everything as a arraylist

		RequestDispatcher rd = request.getRequestDispatcher("/JSP/hr/ViewEmployee.jsp?show=notall");
		rd.forward(request, response);

	}

}