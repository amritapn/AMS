package common.common_data_access;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import common.DateOperation;
import common.pojo_common.Leave;

public class LeaveDataAccess {

	// Checks if a particular leave is present

	public static boolean isPresent(String type) {
		boolean present = false;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from leave where type = ?");
			statement.setString(1, type);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				present = true;
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
		return present;
	}

	// Adds a new leave to the database

	public static int addLeave(Leave leave) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("insert into leave values(?, ?)");
			statement.setString(1, leave.getType());
			statement.setInt(2, leave.getDays());
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
		}
		return queryStatus;
	}

	// Updates the details of a leave in the database

	public static int updateLeave(Leave leave) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("update leave set days = ? where type = ?");
			statement.setInt(1, leave.getDays());
			statement.setString(2, leave.getType());
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
		}
		return queryStatus;
	}

	// Takes the type of a leave and returns the corresponding record from
	// database

	public static Leave getLeaveByName(String type) {
		Leave leave = new Leave();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from leave where type = ?");
			statement.setString(1, type);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				leave.setType(dataHolder.getString(1));
				leave.setDays(dataHolder.getInt(2));
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
		return leave;
	}

	// Returns a List of records present in leave table in database

	public static List<Leave> getAllLeaves() {
		List<Leave> leaveList = new ArrayList<Leave>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from leave");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Leave leave = new Leave();
				leave.setType(dataHolder.getString(1));
				leave.setDays(dataHolder.getInt(2));
				leaveList.add(leave);
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
		return leaveList;
	}

	// Adds the leave details into the waiting_leaves table whenever a new leave
	// is applied

	public static int addToWaitingLeaves(int id, String type, String startDate, String endDate, int duration) {

		int queryStatus = 0;
		String name = null, designation = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			java.sql.Date sqlStartDate = DateOperation.getSqlDate(startDate);
			java.sql.Date sqlEndDate = DateOperation.getSqlDate(endDate);
			statement = connection.prepareStatement("select name, designation from employee_account where emp_id = ?");
			statement.setInt(1, id);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				name = dataHolder.getString(1);
				designation = dataHolder.getString(2);
				statement = connection.prepareStatement("insert into waiting_leaves values(?, ?, ?, ?, ?, ?, ?, ?)");
				statement.setInt(1, id);
				statement.setString(2, name);
				statement.setString(3, designation);
				statement.setDate(4, sqlStartDate);
				statement.setDate(5, sqlEndDate);
				statement.setInt(6, duration);
				statement.setString(7, "Pending");
				statement.setString(8, type);
				queryStatus = statement.executeUpdate();
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
		return queryStatus;

	}

	// Retrieves all pending leaves from the waiting_leaves table and returns a
	// List of those records

	public static List<Leave> getAllPendingLeaves() {

		List<Leave> leaveList = new ArrayList<Leave>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from waiting_leaves where status = ?");
			statement.setString(1, "Pending");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Leave leave = new Leave();
				leave.setId(dataHolder.getInt(1));
				leave.setName(dataHolder.getString(2));
				leave.setDesignation(dataHolder.getString(3));
				java.util.Date date = (java.util.Date) dataHolder.getDate(4);
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String startDate = dateFormat.format(date);
				date = (java.util.Date) dataHolder.getDate(5);
				String endDate = dateFormat.format(date);
				leave.setStartDate(startDate);
				leave.setEndDate(endDate);
				leave.setDays(dataHolder.getInt(6));
				leave.setType(dataHolder.getString(8));
				leaveList.add(leave);
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
		return leaveList;

	}

	// Sets the status as rejected in the waiting_leaves table in the database

	public static int rejectLeave(int id, String type, String startDate, String endDate, int duration) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			java.sql.Date sqlStartDate = DateOperation.getSqlDate(startDate);
			java.sql.Date sqlEndDate = DateOperation.getSqlDate(endDate);
			statement = connection.prepareStatement(
					"update waiting_leaves set status = ? where emp_id = ? and start_date = ? and end_date = ?");
			statement.setString(1, "Rejected");
			statement.setInt(2, id);
			statement.setDate(3, sqlStartDate);
			statement.setDate(4, sqlEndDate);
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
		}
		return queryStatus;
	}

	// Takes the necessary field values and inserts a record into the
	// employee_leave table

	public static int applyLeave(int id, int year, String month, String type, int max, int days) {

		Connection connection = null;
		PreparedStatement statement = null;
		int flag = 0, queryStatus = 0, left = 0;
		try {
			connection = DBConnect.prepareConn();
			statement = connection
					.prepareStatement("insert into employee_leave values(emp_leave_id.nextval, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, id);
			statement.setInt(2, year);
			statement.setString(3, month);
			statement.setString(4, type);
			statement.setInt(5, max);
			statement.setInt(6, days);
			if (days > max) {
				left = 0;
			} else {
				left = max - days;
			}
			statement.setInt(7, left);
			queryStatus = statement.executeUpdate();
			flag = 1;
			JOptionPane.showMessageDialog(null, "Leave application granted successfully.\nLeave Opted For The Month Of "
					+ month + ": " + days + "\nLeave Left For The Month Of " + month + ": " + left);
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

		return flag;

	}

	// Takes the user input and and caluculates the required values for applying
	// leave

	public static int calculateLeave(int id, String type, String startDate, String endDate, int duration) {

		int queryStatus = 0;
		String startMonth = null;
		String endMonth = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			int max = 0, left = 0;
			statement = connection.prepareStatement("select days from leave where type = ?");
			statement.setString(1, type);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				max = dataHolder.getInt(1);
			}

			Calendar startCalendar = Calendar.getInstance();
			startCalendar = DateOperation.getCalendarDate(startDate);

			startMonth = DateOperation.getCurrentMonth(startCalendar.get(Calendar.MONTH));
			int startDay = startCalendar.get(Calendar.DAY_OF_MONTH);
			int startYear = startCalendar.get(Calendar.YEAR);

			Calendar endCalendar = Calendar.getInstance();
			endCalendar = DateOperation.getCalendarDate(endDate);

			endMonth = DateOperation.getCurrentMonth(endCalendar.get(Calendar.MONTH));
			int endDay = endCalendar.get(Calendar.DAY_OF_MONTH);
			int endYear = endCalendar.get(Calendar.YEAR);

			int days = 0;
			int flag = 0;
			if ((startMonth.equals(endMonth)) && (startYear == endYear)) {
				days = duration;
				flag = applyLeave(id, startYear, startMonth, type, max, days);
			} else if ((startYear == endYear) && !(startMonth.equals(endMonth))) {
				// Apply leave for start month
				if (startMonth.equals("January") || startMonth.equals("March") || startMonth.equals("May")
						|| startMonth.equals("July") || startMonth.equals("August") || startMonth.equals("October")
						|| startMonth.equals("December")) {
					days = (31 - startDay) + 1;
					flag = applyLeave(id, startYear, startMonth, type, max, days);
				} else if (startMonth.equals("February")) {
					if (((startYear % 400 == 0) || (startYear % 4 == 0)) && !(startYear % 100 == 0)) {
						days = (29 - startDay) + 1;
					} else {
						days = (28 - startDay) + 1;
					}
					flag = applyLeave(id, startYear, startMonth, type, max, days);
				} else {
					days = (30 - startDay) + 1;
					flag = applyLeave(id, startYear, startMonth, type, max, days);
				}
				// Apply leave for end month
				flag = applyLeave(id, startYear, endMonth, type, max, duration - days);
			} else if ((startMonth.equals("December") && endMonth.equals("January")) && (endYear == startYear + 1)) {
				// Apply leave for start month
				days = (31 - startDay) + 1;
				flag = applyLeave(id, startYear, startMonth, type, max, days);

				// Apply leave for end month
				days = duration - days;
				flag = applyLeave(id, startYear + 1, endMonth, type, max, days);
			} else {
				JOptionPane.showMessageDialog(null, "Sorry, leave can't be applied for so many days.");
			}

			queryStatus = updateLeaveStatus(id, startDate, endDate, flag);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					DBConnect.closeConn(connection);
				}
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return queryStatus;
	}

	// Updates the status of leave applications in database waiting_leaves table

	public static int updateLeaveStatus(int id, String startDate, String endDate, int flag) {

		Connection connection = null;
		PreparedStatement statement = null;
		int queryStatus = 0;
		try {
			connection = DBConnect.prepareConn();
			java.sql.Date sqlStartDate = DateOperation.getSqlDate(startDate);
			java.sql.Date sqlEndDate = DateOperation.getSqlDate(endDate);
			statement = connection.prepareStatement(
					"update waiting_leaves set status = ? where emp_id = ? and start_date = ? and end_date = ?");
			if (flag == 1) {
				statement.setString(1, "Approved");
			} else {
				statement.setString(1, "Rejected");
			}
			statement.setInt(2, id);
			statement.setDate(3, sqlStartDate);
			statement.setDate(4, sqlEndDate);
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
		}

		return queryStatus;

	}
}
