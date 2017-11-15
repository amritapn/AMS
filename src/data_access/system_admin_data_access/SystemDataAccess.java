package data_access.system_admin_data_access;

import java.sql.*;
import java.util.*;

import common.common_data_access.DBConnect;
import common.pojo_common.ForgotPasswordBean;
import common.pojo_common.PasswordBean;

public class SystemDataAccess {

	static Connection connection = null;

	public static ArrayList viewAllAccesses() {
		ArrayList all = new ArrayList();

		try {
			String sql = "SELECT employee_account.emp_id,employee_account.name,employee_account.role,employeeaccessprivilege.adminmain,employeeaccessprivilege.hr,employeeaccessprivilege.accountmanager,employeeaccessprivilege.systemadmin FROM employee_account INNER JOIN employeeaccessprivilege ON employee_account.emp_id = employeeaccessprivilege.emp_id";
			connection = DBConnect.prepareConn();
			Statement st = connection.createStatement();

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				ArrayList one = new ArrayList();
				one.add(rs.getInt(1));
				one.add(rs.getString(2));
				one.add(rs.getString(3));
				one.add(rs.getString(4));
				one.add(rs.getString(5));
				one.add(rs.getString(6));
				one.add(rs.getString(7));
				all.add(one);
			}

			DBConnect.closeConn(connection);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return all;
	}

	public static void updateAccess(boolean admin, boolean hr, boolean account, boolean systemad, int chkId) {

		try {
			// one.get(0);
			// System.out.println(admin+" "+hr+" "+account+" "+systemad);
			String sql1 = "update employeeaccessprivilege set adminmain='" + admin + "',hr='" + hr
					+ "',accountmanager='" + account + "',systemadmin='" + systemad + "' where emp_id = " + chkId + "";
			connection = DBConnect.prepareConn();
			// PreparedStatement state = c.prepareStatement(sql1);
			Statement state1 = connection.createStatement();
			ResultSet result = state1.executeQuery(sql1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String checkPass(PasswordBean pass) {
		try {

			String newPass = pass.getNewPass();
			String oldPass = pass.getOldPass();
			int id = pass.getId();
			String query1 = "select password from employee_account where emp_id = " + id + " ";
			String query = "update employee_account set password = '" + newPass + "' where emp_id = " + id
					+ " and password = '" + oldPass + "' ";

			connection = DBConnect.prepareConn();

			Statement state = connection.createStatement();
			ResultSet result1 = state.executeQuery(query1);
			String returnPass = null;

			while (result1.next())
				returnPass = result1.getString(1);
			ResultSet result = state.executeQuery(query);

			return returnPass;

		} catch (Exception e) {

			return e.getMessage();
		}
	}

	public static String forgotPass(ForgotPasswordBean pass) {
		try {
			String newPass = pass.getNewPass();
			String rePass = pass.getRePass();
			String email = pass.getEmail();
			String returnPass = null;

			String query = "update employee_account set password = '" + newPass + "' where email = '" + email + "' ";
			connection = DBConnect.prepareConn();

			Statement state = connection.createStatement();
			ResultSet result = state.executeQuery(query);

			return rePass;
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
