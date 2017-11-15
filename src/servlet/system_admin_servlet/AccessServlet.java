package servlet.system_admin_servlet;

import javax.servlet.*;
import javax.servlet.http.*;

import data_access.system_admin_data_access.SystemDataAccess;
import pojo.system_admin_pojo.AccessPrivilege;

import java.io.*;
import java.sql.*;

public class AccessServlet extends HttpServlet {

	Connection connection;
	PrintWriter out;
	boolean adminChk;
	boolean hrChk;
	boolean accountChk;
	boolean systemChk;

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {

			String admin = request.getParameter("hiddenchangeAdmin");
			String hr = request.getParameter("hiddenchangeHr");
			String account = request.getParameter("hiddenchangeAccount");
			String systemad = request.getParameter("hiddenchangeSystemad");
			int chkId = Integer.parseInt(request.getParameter("hid"));
			out = response.getWriter();

			// out.println(chkId);
			// System.out.println(" "+admin+" "+hr+" "+account+" "+systemad);

			if (admin.equals("on"))
				adminChk = true;
			else
				adminChk = false;

			if (hr.equals("on"))
				hrChk = true;
			else
				hrChk = false;

			if (account.equals("on"))
				accountChk = true;
			else
				accountChk = false;

			if (systemad.equals("on"))
				systemChk = true;
			else
				systemChk = false;

			// System.out.println(" "+adminChk+" "+hrChk+" "+accountChk+"
			// "+systemChk);

			AccessPrivilege access = new AccessPrivilege();
			access.setAdmin(adminChk);
			access.setHr(hrChk);
			access.setAccountManager(accountChk);
			access.setSystemAdmin(systemChk);
			SystemDataAccess.updateAccess(adminChk, hrChk, accountChk, systemChk, chkId);
			RequestDispatcher rd = request.getRequestDispatcher("/JSP/system_admin/AccessPrivilege.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
	}
}