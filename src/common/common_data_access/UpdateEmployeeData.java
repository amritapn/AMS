package common.common_data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import common.pojo_common.JBean;

public class UpdateEmployeeData {

	ArrayList empDetails = new ArrayList();
	int firstIsEdited = 0, secondIsEdited = 0;

	public String updateDatabase(Connection connect, JBean jBean) {

		try {
			// get the data from Bean

			empDetails = jBean.getUpdatedEmpDetails();
			int emp_id = jBean.getEmpId();
			// setting the query
			String sql = "update employee_account set name=(?),designation=(?),phone=(?),email=(?),plot_no=(?),city=(?),state=(?),pin=(?) where emp_id=(?)";

			String sql2 = "update employee_salary set name=(?),basic_salary=(?),ta=(?),da=(?),hra=(?),epf=(?) where emp_id=(?)";

			// sql statement

			PreparedStatement pStatement = connect.prepareStatement(sql);

			pStatement.setString(1, (empDetails.get(1)).toString());// name
			pStatement.setString(2, (empDetails.get(2)).toString());// designation
			pStatement.setString(3, (empDetails.get(3)).toString());// phone
			pStatement.setString(4, (empDetails.get(4)).toString());// email
			pStatement.setString(5, (empDetails.get(5)).toString());// plot_no
			pStatement.setString(6, (empDetails.get(6)).toString());// city
			pStatement.setString(7, (empDetails.get(7)).toString());// state
			pStatement.setString(8, (empDetails.get(8)).toString());// pin

			pStatement.setInt(9, Integer.parseInt((empDetails.get(0)).toString()));

			firstIsEdited = pStatement.executeUpdate();

			// if the first table is updated then the 2nd table should alse be
			// updated.

			if (firstIsEdited == 1) {

				// sql statement this block is not working

				PreparedStatement pStatement2 = connect.prepareStatement(sql2);

				pStatement2.setString(1, (empDetails.get(1)).toString());// name
				pStatement2.setDouble(2, Double.parseDouble((empDetails.get(9)).toString()));// basic
				pStatement2.setInt(3, Integer.parseInt((empDetails.get(10)).toString()));// ta
				pStatement2.setInt(4, Integer.parseInt((empDetails.get(11)).toString()));// da
				pStatement2.setInt(5, Integer.parseInt((empDetails.get(12)).toString()));// hra
				pStatement2.setInt(6, Integer.parseInt((empDetails.get(13)).toString()));// epf

				pStatement2.setInt(7, Integer.parseInt((empDetails.get(0)).toString()));

				secondIsEdited = pStatement2.executeUpdate();

				if (secondIsEdited == 1) {

					return "second is true";
				} else {
					return "second is false";
				}

			} else {
				return "first is false";
			}

		} catch (Exception e) {
			return e.getMessage();
		}

	}

}