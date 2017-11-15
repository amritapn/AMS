package data_access.system_admin_data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import common.common_data_access.DBConnect;
import pojo.system_admin_pojo.Designation;

public class DesignationDataAccess {

	/*public static Connection getConnection() {
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

	public static int addDesignation(Designation designation) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("insert into designation values(?, ?)");
			statement.setString(1, designation.getDesignation());
			statement.setString(2, designation.getRole());
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

	public static int updateDesignation(Designation designation) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("update designation set role_name = ? where designation_name = ?");
			statement.setString(1, designation.getRole());
			statement.setString(2, designation.getDesignation());
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

	public static boolean isPresent(String designation) {
		boolean present = false;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from designation where designation_name = ?");
			statement.setString(1, designation);
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

}