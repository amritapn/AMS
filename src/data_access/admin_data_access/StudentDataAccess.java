package data_access.admin_data_access;

import java.util.*;

import common.common_data_access.DBConnect;
import pojo.admin_pojo.Student;

import java.sql.*;
import java.text.SimpleDateFormat;

public class StudentDataAccess {
	/*static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.66:1521:orcl12c", "AMANDA_TRAINING",
					"Amanda_Training");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}*/

	public static int add(Student student, String date) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			int courseId = CourseDataAccess.getCourseIdByName(student.getCourse());
			java.sql.Date dob = new java.sql.Date(student.getDob().getTime());
			java.sql.Date doj = new java.sql.Date(student.getDoj().getTime());
			statement = connection.prepareStatement(
					"insert into student_account values(stud_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, student.getName());
			statement.setString(2, student.getGender());
			statement.setDate(3, dob);
			statement.setString(4, student.getPhone());
			statement.setString(5, student.getEmail());
			statement.setString(6, student.getPlot());
			statement.setString(7, student.getCity());
			statement.setString(8, student.getState());
			statement.setString(9, student.getPin());
			statement.setInt(10, courseId);
			statement.setDate(11, doj);
			statement.setString(12, student.getPaymentType());
			statement.setDouble(13, student.getInstallmentAmount());
			statement.setString(14, student.getInstallmentDuration());
			statement.setDouble(15, student.getPaid());
			statement.setDouble(16, student.getPaymentPending());
			statement.setString(17, student.getStatus());
			queryStatus = statement.executeUpdate();
			if (queryStatus > 0) {
				String month = null;
				int id = 0;
				statement = connection.prepareStatement("select stud_id from student_account where email = ?");
				statement.setString(1, student.getEmail());
				ResultSet dataHolder = statement.executeQuery();
				if (dataHolder.next()) {
					id = dataHolder.getInt(1);
				}
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
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int year = calendar.get(Calendar.YEAR);
				statement = connection.prepareStatement(
						"insert into student_payment_details values(student_payment_id.nextval, ?, ?, ?, ?, ?)");
				statement.setInt(1, id);
				statement.setInt(2, day);
				statement.setString(3, month);
				statement.setInt(4, year);
				statement.setDouble(5, student.getPaid());
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

	public static List<Student> viewAll() {
		List<Student> studentList = new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from student_account order by stud_id");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Student student = new Student();
				student.setId(dataHolder.getInt(1));
				student.setName(dataHolder.getString(2));
				student.setGender(dataHolder.getString(3));
				java.sql.Date date = dataHolder.getDate(4);
				java.util.Date javaDate = (java.util.Date) date;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dob = dateFormat.format(javaDate);
				student.setDob(dob);
				student.setPhone(dataHolder.getString(5));
				student.setEmail(dataHolder.getString(6));
				student.setPlot(dataHolder.getString(7));
				student.setCity(dataHolder.getString(8));
				student.setState(dataHolder.getString(9));
				student.setPin(dataHolder.getString(10));
				String course = CourseDataAccess.getCourseNameById(dataHolder.getInt(11));
				student.setCourse(course);
				date = dataHolder.getDate(12);
				javaDate = (java.util.Date) date;
				String doj = dateFormat.format(javaDate);
				student.setDoj(doj);
				student.setPaymentType(dataHolder.getString(13));
				student.setInstallmentAmount(dataHolder.getDouble(14));
				student.setInstallmentDuration(dataHolder.getString(15));
				student.setPaid(dataHolder.getDouble(16));
				student.setPaymentPending(dataHolder.getDouble(17));
				student.setStatus(dataHolder.getString(18));
				studentList.add(student);
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
		return studentList;
	}

	public static Student getStudentById(int id) {
		Student student = new Student();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from student_account where stud_id = ?");
			statement.setInt(1, id);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				student.setId(dataHolder.getInt(1));
				student.setName(dataHolder.getString(2));
				student.setGender(dataHolder.getString(3));
				java.sql.Date date = dataHolder.getDate(4);
				java.util.Date javaDate = (java.util.Date) date;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dob = dateFormat.format(javaDate);
				student.setDob(dob);
				student.setPhone(dataHolder.getString(5));
				student.setEmail(dataHolder.getString(6));
				student.setPlot(dataHolder.getString(7));
				student.setCity(dataHolder.getString(8));
				student.setState(dataHolder.getString(9));
				student.setPin(dataHolder.getString(10));
				String course = CourseDataAccess.getCourseNameById(dataHolder.getInt(11));
				student.setCourse(course);
				date = dataHolder.getDate(12);
				javaDate = (java.util.Date) date;
				String doj = dateFormat.format(javaDate);
				student.setDoj(doj);
				student.setPaymentType(dataHolder.getString(13));
				student.setInstallmentAmount(dataHolder.getDouble(14));
				student.setInstallmentDuration(dataHolder.getString(15));
				student.setPaid(dataHolder.getDouble(16));
				student.setPaymentPending(dataHolder.getDouble(17));
				student.setStatus(dataHolder.getString(18));
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
		return student;
	}

	public static int edit(Student student) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			int courseId = CourseDataAccess.getCourseIdByName(student.getCourse());
			java.sql.Date dob = new java.sql.Date(student.getDob().getTime());
			java.sql.Date doj = new java.sql.Date(student.getDoj().getTime());
			statement = connection.prepareStatement(
					"update student_account set name = ?, gender = ?, dob = ?, phone = ?, email = ?, plot = ?, city = ?, state = ?, pin = ?, course_id = ?, doj = ?, status = ? where stud_id = ?");
			statement.setString(1, student.getName());
			statement.setString(2, student.getGender());
			statement.setDate(3, dob);
			statement.setString(4, student.getPhone());
			statement.setString(5, student.getEmail());
			statement.setString(6, student.getPlot());
			statement.setString(7, student.getCity());
			statement.setString(8, student.getState());
			statement.setString(9, student.getPin());
			statement.setInt(10, courseId);
			statement.setDate(11, doj);
			statement.setString(12, student.getStatus());
			statement.setInt(13, student.getId());
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

	public static int pay(int id, double amount, String date) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			Student student = getStudentById(id);
			student.setPaid(student.getPaid() + amount);
			student.setPaymentPending(student.getPaymentPending() - amount);
			statement = connection
					.prepareStatement("update student_account set paid = ?, payment_pending = ? where stud_id = ?");
			statement.setDouble(1, student.getPaid());
			statement.setDouble(2, student.getPaymentPending());
			statement.setInt(3, student.getId());
			queryStatus = statement.executeUpdate();
			if (queryStatus > 0) {
				String month = null;
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
				int day = calendar.get(Calendar.DAY_OF_MONTH);
				int year = calendar.get(Calendar.YEAR);
				statement = connection.prepareStatement(
						"insert into student_payment_details values(student_payment_id.nextval, ?, ?, ?, ?, ?)");
				statement.setInt(1, id);
				statement.setInt(2, day);
				statement.setString(3, month);
				statement.setInt(4, year);
				statement.setDouble(5, amount);
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
}
