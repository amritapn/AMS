package common.common_data_access;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.servlet.http.*;
import javax.servlet.*;
import common.pojo_common.*;

public class GetSearchResult {

	ArrayList empList = new ArrayList();
	ArrayList list2;

	public Collection returnEmpList(Connection connect, JBean jBean) {

		// Get all the datas first from the bean class

		String searchEmpName = jBean.getSearchEmpName();
		String searchEmpDesignation = jBean.getSearchEmpDesignation();
		String searchEmpStatus = jBean.getSearchEmpStatus();
		// if(jBean.getSearchEmpId.equals(""))

		try {

			// search from database.
			if (jBean.getSearchEmpId().equals("") && !searchEmpName.equals("")) {

				// when id is not present but name is given
				String sqlQuery = "select employee_account.emp_id,employee_account.name,employee_account.designation,employee_account.phone,employee_account.email,employee_account.doj,employee_account.status,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id = employee_salary.emp_id and employee_account.name='"
						+ searchEmpName + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					empList.add(list.getInt(1));
					empList.add(list.getString(2));
					empList.add(list.getString(3));
					empList.add(list.getLong(4));
					empList.add(list.getString(5));
					empList.add(list.getLong(8));
					empList.add(list.getDate(6));
					empList.add(list.getString(7));

				}

			} else if (searchEmpName.equals("") && !jBean.getSearchEmpId().equals("")) {

				// if id is given but name is not

				int searchEmpId = Integer.parseInt(jBean.getSearchEmpId());

				String sqlQuery = "select employee_account.emp_id,employee_account.name,employee_account.designation,employee_account.phone,employee_account.email,employee_account.doj,employee_account.status,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id = employee_salary.emp_id and employee_account.emp_id='"
						+ searchEmpId + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					empList.add(list.getInt(1));
					empList.add(list.getString(2));
					empList.add(list.getString(3));
					empList.add(list.getLong(4));
					empList.add(list.getString(5));
					empList.add(list.getLong(8));
					empList.add(list.getDate(6));
					empList.add(list.getString(7));

				}

			} else if (!searchEmpName.equals("") && !jBean.getSearchEmpId().equals("")) {

				// if id and name both are given on the field

				int searchEmpId = Integer.parseInt(jBean.getSearchEmpId());

				String sqlQuery = "select employee_account.emp_id,employee_account.name,employee_account.designation,employee_account.phone,employee_account.email,employee_account.doj,employee_account.status,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id = employee_salary.emp_id and employee_account.emp_id='"
						+ searchEmpId + "'";

				Statement pStatement = connect.createStatement();
				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					empList.add(list.getInt(1));
					empList.add(list.getString(2));
					empList.add(list.getString(3));
					empList.add(list.getLong(4));
					empList.add(list.getString(5));
					empList.add(list.getLong(8));
					empList.add(list.getDate(6));
					empList.add(list.getString(7));

				}

			} else
				if (searchEmpName.equals("") && jBean.getSearchEmpId().equals("") && !searchEmpDesignation.equals("")) {

				// if id and name both are absent but only designation is
				// present.

				String sqlQuery = "select employee_account.emp_id,employee_account.name,employee_account.designation,employee_account.phone,employee_account.email,employee_account.doj,employee_account.status,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id = employee_salary.emp_id and employee_account.designation='"
						+ searchEmpDesignation + "'";

				Statement pStatement = connect.createStatement();

				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					empList.add(list.getInt(1));
					empList.add(list.getString(2));
					empList.add(list.getString(3));
					empList.add(list.getLong(4));
					empList.add(list.getString(5));
					empList.add(list.getLong(8));
					empList.add(list.getDate(6));
					empList.add(list.getString(7));

				}

			} else if (searchEmpName.equals("") && jBean.getSearchEmpId().equals("") && searchEmpDesignation.equals("")
					&& !searchEmpStatus.equals("")) {

				// if only status is given
				String sqlQuery = "select employee_account.emp_id,employee_account.name,employee_account.designation,employee_account.phone,employee_account.email,employee_account.doj,employee_account.status,employee_salary.gross_salary from employee_account inner join employee_salary on employee_account.emp_id = employee_salary.emp_id and employee_account.status='Active'";
				Statement pStatement = connect.createStatement();

				// pStatement.setString(1,searchEmpStatus);

				ResultSet list = pStatement.executeQuery(sqlQuery);

				while (list.next()) {

					empList.add(list.getInt(1));
					empList.add(list.getString(2));
					empList.add(list.getString(3));
					empList.add(list.getLong(4));
					empList.add(list.getString(5));
					empList.add(list.getLong(8));
					empList.add(list.getDate(6));
					empList.add(list.getString(7));

				}

			}

			// empList.add(searchEmpId);

			return empList;
		} catch (SQLException sqe) {
			empList.add("Invalid");
			empList.add(sqe.getMessage());
			return empList;
		}

	}

}
