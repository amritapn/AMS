package common.servlet_common;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.*;

import common.common_data_access.DBConnect;
import common.common_data_access.UpdateEmployeeData;
import common.pojo_common.JBean;

public class UpdateEmployee extends HttpServlet {

	Connection connect;
	ArrayList empDetails = new ArrayList();
	ArrayList updatedEmployeeList = new ArrayList();
	String isUpdated;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		try {
			String empId = request.getParameter("id");
			int id = Integer.parseInt(empId);

			String name = request.getParameter("name");
			String designation = request.getParameter("designation");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");

			String plot = request.getParameter("plot");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String pin = request.getParameter("pin");

			String salary = request.getParameter("salary");
			String ta = request.getParameter("ta");
			String da = request.getParameter("da");
			String hra = request.getParameter("hra");
			String epf = request.getParameter("epf");

			String status = request.getParameter("status");

			// Adding the data to an arrayList
			empDetails.add(id);
			empDetails.add(name);
			empDetails.add(designation);
			empDetails.add(phone);
			empDetails.add(email);
			empDetails.add(plot);
			empDetails.add(city);
			empDetails.add(state);
			empDetails.add(pin);
			empDetails.add(salary);
			empDetails.add(ta);
			empDetails.add(da);
			empDetails.add(hra);
			empDetails.add(epf);
			empDetails.add(status);

			// out.println(empDetails.get(14));

			connect = DBConnect.prepareConn(); // connection to DBConenct for
												// connection to database.
			// Now after connection is set we need to call the bean to set all
			// the data.

			// Bean Job
			JBean jBean = new JBean();
			jBean.setEmpId(id);
			jBean.setUpdatedEmpDetails(empDetails);
			// -----------------------------

			// Now Call the login.java that would take the bean object and
			// connection objectand upload data to the database
			UpdateEmployeeData updateEmployeeData = new UpdateEmployeeData();
			isUpdated = updateEmployeeData.updateDatabase(connect, jBean);

			out.println(isUpdated);
			out.println("<script language='javascript'>");
		       out.println("window.alert('Record updated successfully.');");
		       out.println("window.location='"+path+"/JSP/hr/ViewEmployee.jsp?show=all'");
		       out.println("</script>");
			

			empDetails.clear(); // Clears the arraylist for next operation.
			// important to use otherwise the data gets piling up and editing
			// task fails.

		} catch (Exception e) {
			out.println(e.getMessage());
			out.println("<script> alert('Error Found in Data Passing.'); </script>");

		}

	}

}