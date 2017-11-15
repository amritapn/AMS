package data_access.admin_data_access;

import java.sql.*;
import java.util.*;

import common.common_data_access.DBConnect;
import pojo.admin_pojo.Course;

public class CourseDataAccess {
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

	public static int getCourseIdByName(String courseName) {
		int courseId = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select course_id from course where name = ?");
			statement.setString(1, courseName);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				courseId = dataHolder.getInt(1);
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
		return courseId;
	}

	public static String getCourseNameById(int courseId) {
		String courseName = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select name from course where course_id = ?");
			statement.setInt(1, courseId);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				courseName = dataHolder.getString(1);
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
		return courseName;
	}

	public static int addCourse(Course course) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("insert into course values (course_id.nextval, ?, ?, ?)");
			statement.setString(1, course.getName());
			statement.setString(2, course.getDuration());
			statement.setDouble(3, course.getFees());
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

	public static int updateCourse(Course course) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("update course set duration = ?, fees = ? where name = ?");
			statement.setString(1, course.getDuration());
			statement.setDouble(2, course.getFees());
			statement.setString(3, course.getName());
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

	public static List<Course> viewAllCourse() {
		List<Course> courseList = new ArrayList<Course>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from course order by course_id");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Course course = new Course();
				course.setId(dataHolder.getInt(1));
				course.setName(dataHolder.getString(2));
				course.setDuration(dataHolder.getString(3));
				course.setFees(dataHolder.getDouble(4));
				courseList.add(course);
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
		return courseList;
	}
}
