package common.servlet_common;

/*
* Validate.java gets the mail and password and calls 
setUserId() and setPasswrd() of the JBean class that sets 
it locally.
*Then JBean checks for the validation of the credentials.
* If present then the boolean return type method
isValid() returns true.
* If true then the user is redirected to a s.jsp page theat shows 
a admin dashboard.

*/
/*
* Validate.java gets the mail and password and calls 
setUserId() and setPasswrd() of the JBean class that sets 
it locally.
*Then JBean checks for the validation of the credentials.
* If present then the boolean return type method
isValid() returns true.
* If true then the user is redirected to a s.jsp page theat shows 
a admin dashboard.

*/
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.swing.*;

import common.common_data_access.DBConnect;
import common.common_data_access.Login;
import common.pojo_common.JBean;

public class LoginControllerServlet extends HttpServlet {
	ArrayList privileges = new ArrayList();
	ArrayList empDetails = new ArrayList();
	ArrayList loginCredential;
	String email, password;
	Connection connect;
	RequestDispatcher rd;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String path = (String)request.getContextPath();
		email = request.getParameter("mail");
		password = request.getParameter("pass");

		connect = DBConnect.prepareConn(); // connection to DBConenct for
											// connection to database.
		// Now after connection is set we need to call the bean to set all the
		// data.

		// Bean Job
		JBean jBean = new JBean();
		jBean.setEmail(email);
		jBean.setPassword(password);
		// -----------------------------

		// Now Call the login.java that would take the bean object and
		// connection objectand upload data to the database

		Login login = new Login();

		Object returnedObject = new Object();
		returnedObject = login.loginMain(jBean);

		String returnedMessage = jBean.getMessage();

		// check if it is false or true
		empDetails = jBean.getLoginCredential();

		session.setAttribute("empDetails", empDetails);

		/*
		 * For testing purposes only
		 * 
		 * //out.println(returnedMessage); for(int i=0;i<empDetails.size();i++){
		 * out.println(empDetails.get(i)); }
		 */

		if (returnedMessage.equals("Login Exception")) {
			out.println("<script language='javascript'>");
		       out.println("window.alert('Invalid Email/Password.');");
		       out.println("window.location='"+path+"/JSP/commonjsp/Login.jsp'");
		       out.println("</script>");
		       
		}

		else if (returnedMessage.equals("login")) {
			
		

		session.setAttribute("user_name", (empDetails.get(1).toString()));
		session.setAttribute("emp_id", empDetails.get(0));
		rd = request.getRequestDispatcher("/JSP/commonjsp/HomePage.jsp");
		rd.forward(request, response);
		}
	}

}