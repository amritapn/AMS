package data_access.account_manager_data_access;

import pojo.account_manager_pojo.*;
import common.pojo_common.*;
import common.*;
import common.common_data_access.DBConnect;

import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class EmployeeSalaryDataAccess {
	/*
	 * public static Connection getConnection() { Connection connection = null;
	 * try { Class.forName("oracle.jdbc.driver.OracleDriver"); connection =
	 * DriverManager.getConnection(
	 * "jdbc:oracle:thin:@192.168.0.66:1521:orcl12c", "AMANDA_TRAINING",
	 * "Amanda_Training"); } catch (Exception e) { e.printStackTrace(); } return
	 * connection; }
	 */

	public static List<EmployeeSalary> getEmployeeSalaryData() {
		List<EmployeeSalary> employeeSalaryList = new ArrayList<EmployeeSalary>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from employee_salary order by emp_id");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				EmployeeSalary employeeSalary = new EmployeeSalary();
				Salary salary = new Salary();
				employeeSalary.setEmpId(dataHolder.getInt(1));
				employeeSalary.setEmpName(dataHolder.getString(2));
				salary.setBasicSalary(dataHolder.getDouble(3));
				salary.setTa(dataHolder.getInt(4));
				salary.setDa(dataHolder.getInt(5));
				salary.setHra(dataHolder.getInt(6));
				salary.setEpf(dataHolder.getInt(7));
				salary.setGrossSalary(dataHolder.getDouble(8));
				employeeSalary.setSalary(salary);
				employeeSalaryList.add(employeeSalary);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					DBConnect.closeConn(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return employeeSalaryList;
	}

	public static int getWorkingDaysByMonth(String month, int year) {
		int workingDays = 0;

		switch (month) {
		case "January":
			workingDays = 31 - 4;
			break;
		case "February":
			if (((year % 400 == 0) || (year % 4 == 0)) && !(year % 100 == 0)) {
				workingDays = 29 - 4;
			} else {
				workingDays = 28 - 4;
			}
			break;
		case "March":
			workingDays = 31 - 4;
			break;
		case "April":
			workingDays = 30 - 4;
			break;
		case "May":
			workingDays = 31 - 4;
			break;
		case "June":
			workingDays = 30 - 4;
			break;
		case "July":
			workingDays = 31 - 4;
			break;
		case "August":
			workingDays = 31 - 4;
			break;
		case "September":
			workingDays = 30 - 4;
			break;
		case "October":
			workingDays = 31 - 4;
			break;
		case "November":
			workingDays = 30 - 4;
			break;
		case "December":
			workingDays = 31 - 4;
			break;
		}

		return workingDays;
	}

	public static int paySalary(int empId, String date, Double grossSalary) {
		int queryStatus = 0;
		String month = null, month1 = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(date));
			int thisMonth = calendar.get(Calendar.MONTH);
			int previousMonth = thisMonth - 1;
			int year = calendar.get(Calendar.YEAR);
			int workingDays = 0;

			month = DateOperation.getCurrentMonth(thisMonth);
			month1 = DateOperation.getPreviousMonth(previousMonth);
			workingDays = EmployeeSalaryDataAccess.getWorkingDaysByMonth(month1, year);

			statement = connection.prepareStatement(
					"select max, leave_opted from employee_leave where emp_id = ? and month = ? and year = ?");
			statement.setInt(1, empId);
			statement.setString(2, month1);
			if (month.equals("January")) {
				statement.setInt(3, (year - 1));
			} else {
				statement.setInt(3, year);
			}
			ResultSet dataHolder = statement.executeQuery();

			int extraLeaves = 0;
			while (dataHolder.next()) {
				int maxLeave = dataHolder.getInt(1);
				int leaveOpted = dataHolder.getInt(2);
				if (leaveOpted > maxLeave) {
					extraLeaves += (leaveOpted - maxLeave);
				}
			}

			double salaryDeducted = extraLeaves * (grossSalary / workingDays);
			double salary = grossSalary - salaryDeducted;
			statement = connection
					.prepareStatement("insert into employee_payment_details values(payment_id.nextval, ?, ?, ?, ?, ?)");
			statement.setInt(1, empId);
			statement.setInt(2, calendar.get(Calendar.DAY_OF_MONTH));
			statement.setString(3, month);
			statement.setInt(4, year);
			statement.setDouble(5, salary);
			queryStatus = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					DBConnect.closeConn(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return queryStatus;
		}
	}

	public static double viewMonthWise(int id, int year, String month) {
		double totalSalary = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement(
					"select gross_salary from employee_payment_details where emp_id = ? and month = ? and year = ?");
			statement.setInt(1, id);
			statement.setString(2, month);
			statement.setInt(3, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				totalSalary += dataHolder.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					DBConnect.closeConn(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return totalSalary;
		}
	}

	public static double getTotalSalaryPaidByYear(int year) {
		double totalSalaryPaid = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select gross_salary from employee_payment_details where year = ?");
			statement.setInt(1, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				totalSalaryPaid += dataHolder.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					DBConnect.closeConn(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return totalSalaryPaid;
		}
	}

	public static double getGrossSalaryById(int id) {
		double grossSalary = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select gross_salary from employee_salary where emp_id = ?");
			statement.setInt(1, id);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				grossSalary = dataHolder.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					DBConnect.closeConn(connection);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return grossSalary;
		}
	}
}