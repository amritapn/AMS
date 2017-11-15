package common.common_data_access;

import data_access.account_manager_data_access.*;
import common.pojo_common.*;
import java.sql.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class ExpenseDataAccess {
	/*
	 * public static Connection getConnection() { Connection connection = null;
	 * try { Class.forName("oracle.jdbc.driver.OracleDriver"); connection =
	 * DriverManager.getConnection(
	 * "jdbc:oracle:thin:@192.168.0.66:1521:orcl12c", "AMANDA_TRAINING",
	 * "Amanda_Training"); } catch (Exception e) { e.printStackTrace(); } return
	 * connection; }
	 */

	public static double getTotalExpense(int day, String month, int year) {

		double totalExpense = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement(
					"select amount from organisational_expenses where day = ? and month = ? and year = ?");
			statement.setInt(1, day);
			statement.setString(2, month);
			statement.setInt(3, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {

				double expenseAmount = dataHolder.getDouble(1);
				totalExpense = totalExpense + expenseAmount;
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
		return totalExpense;
	}

	public static double getTotalExpenseByYear(int year) {
		double totalExpense = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select amount from organisational_expenses where year = ?");
			statement.setInt(1, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				totalExpense += dataHolder.getDouble(1);
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
		return totalExpense;
	}

	public static double getTotalIncome(int year) {
		double totalIncome = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select fees_paid from student_payment_details where year = ?");
			statement.setInt(1, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				totalIncome += dataHolder.getDouble(1);
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
		return totalIncome;
	}

	public static double getTurnOver(int year, int tax) {
		double turnOver = 0.0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			double totalIncome = getTotalIncome(year);
			double totalExpense = getTotalExpenseByYear(year);
			double totalSalaryPaid = EmployeeSalaryDataAccess.getTotalSalaryPaidByYear(year);
			double taxAmount = totalIncome * (tax / 100);
			turnOver = totalIncome - (taxAmount + totalSalaryPaid + totalExpense);
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
		return turnOver;
	}

	public static int addExpense(Expense expense) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("insert into organisational_expenses values(?, ?, ?, ?, ?)");
			statement.setInt(1, expense.getDay());
			statement.setString(2, expense.getMonth());
			statement.setInt(3, expense.getYear());
			statement.setString(4, expense.getType());
			statement.setDouble(5, expense.getAmount());
			queryStatus = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBConnect.closeConn(connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return queryStatus;
	}

	public static Expense storeDate(String date, Expense expense) {
		String month = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(format.parse(date));

			switch (calendar.get(Calendar.MONTH)) {
			case 0:
				month = "January";
				break;
			case 1:
				month = "February";
				break;
			case 2:
				month = "March";
				break;
			case 3:
				month = "April";
				break;
			case 4:
				month = "May";
				break;
			case 5:
				month = "June";
				break;
			case 6:
				month = "July";
				break;
			case 7:
				month = "August";
				break;
			case 8:
				month = "September";
				break;
			case 9:
				month = "October";
				break;
			case 10:
				month = "November";
				break;
			case 11:
				month = "December";
				break;
			}
			expense.setDay(calendar.get(Calendar.DAY_OF_MONTH));
			expense.setMonth(month);
			expense.setYear(calendar.get(Calendar.YEAR));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expense;
	}

	public static List<Expense> viewAll(String month, int year) {
		List<Expense> expenseList = new ArrayList<Expense>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement(
					"select day, type, amount from organisational_expenses where month = ? and year = ?");
			statement.setString(1, month);
			statement.setInt(2, year);
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Expense expense = new Expense();
				expense.setDay(dataHolder.getInt(1));
				expense.setType(dataHolder.getString(2));
				expense.setAmount(dataHolder.getDouble(3));
				expenseList.add(expense);
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
		return expenseList;
	}
}
