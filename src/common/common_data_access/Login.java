package common.common_data_access;

/*
Takes the connection object and bean class objects
and after query returns collection the logincredentials

*/
/*
Takes the connection object and bean class objects
and after query returns collection the logincredentials

*/
import common.pojo_common.*;
import java.sql.*;
import java.util.*;

public class Login {
	ArrayList loginCredential = new ArrayList(); // for storing user credentials
	ArrayList empDetails_ForHomePage = new ArrayList();
	Connection connect = DBConnect.prepareConn(); // for connection to db

	public Object loginMain(JBean jBean) {

		String email = jBean.getEmail(); // Gets the useremail from bean
		String password = jBean.getPassword(); // Gets the passwordword from
												// bean

		// now calls the query
		try {

			PreparedStatement pStatement = connect
					.prepareStatement("select * from employee_account where email=(?) and password=(?)");

			pStatement.setString(1, email);
			pStatement.setString(2, password);

			ResultSet loginList = pStatement.executeQuery();

			while (loginList.next()) {

				loginCredential.add(loginList.getInt("emp_id"));// 0
				jBean.setId(loginList.getInt("emp_id"));
				loginCredential.add(loginList.getString("name"));// 1
				loginCredential.add(loginList.getDate("dob"));// 2
				loginCredential.add(loginList.getString("phone"));// 3
				loginCredential.add(loginList.getString("email"));// 4
				loginCredential.add(loginList.getString("plot_no"));// 5
				loginCredential.add(loginList.getString("city"));// 6
				loginCredential.add(loginList.getString("state"));// 7
				loginCredential.add(loginList.getString("pin"));// 8
				loginCredential.add(loginList.getString("designation"));// 9
				loginCredential.add(loginList.getDate("doj"));// 10
				loginCredential.add(loginList.getString("status"));// 11
				loginCredential.add(loginList.getString("role"));// 12

			}

			if (loginCredential.get(0) != null) {

				// if we got the information from the 1st table, then execute
				// this block-
				// try{
				PreparedStatement pStatement2 = connect
						.prepareStatement("select * from employeeaccessprivilege where emp_id=(?)");

				pStatement2.setInt(1, jBean.getId());

				ResultSet credentialList = pStatement2.executeQuery();

				while (credentialList.next()) {

					loginCredential.add(credentialList.getString("adminmain"));// 13
					loginCredential.add(credentialList.getString("hr"));// 14
					loginCredential.add(credentialList.getString("accountmanager"));// 15
					loginCredential.add(credentialList.getString("systemadmin"));// 16
				}

				jBean.setMessage("login");
				jBean.setLoginCredential(loginCredential);
				return jBean;

			} else {
				jBean.setMessage("Invalid Login");
				return jBean;
			}

		} catch (SQLException sqe) {
			jBean.setMessage("SQL Exception");
			return jBean;
		} catch (Exception e) {
			jBean.setMessage("Login Exception");// incase username password is
												// incorrect.
			return jBean;
		}

	}

	public ArrayList loadEmployeeInformation(int emp_id) {

		// loads the emp_id from JBean.
		// got the emp_id

		// now fetch all the data from database.
		String sqlQuery = "select * from employee_account where emp_id=(?)";
		try {
			PreparedStatement empDetailStatement = connect.prepareStatement(sqlQuery);

			empDetailStatement.setInt(1, emp_id);

			ResultSet empDetails = empDetailStatement.executeQuery();

			while (empDetails.next()) {
				empDetails_ForHomePage.add(empDetails.getString("name"));
				empDetails_ForHomePage.add(empDetails.getDate("dob"));
				empDetails_ForHomePage.add(empDetails.getString("phone"));
				empDetails_ForHomePage.add(empDetails.getString("eemail"));
				empDetails_ForHomePage.add(empDetails.getString("plot_no"));
				empDetails_ForHomePage.add(empDetails.getString("city"));
				empDetails_ForHomePage.add(empDetails.getString("state"));
				empDetails_ForHomePage.add(empDetails.getString("pin"));
				empDetails_ForHomePage.add(empDetails.getString("designation"));
				empDetails_ForHomePage.add(empDetails.getDate("doj"));
				empDetails_ForHomePage.add(empDetails.getString("status"));
				empDetails_ForHomePage.add(empDetails.getString("role"));
			}

		} catch (Exception det) {
			empDetails_ForHomePage.add("Error fetching the data.");
		}

		return empDetails_ForHomePage;
	}

}