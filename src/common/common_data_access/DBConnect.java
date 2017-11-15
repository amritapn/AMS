package common.common_data_access;

import java.sql.*;
import java.io.*;
import java.util.*;

public class DBConnect {

	public static Connection prepareConn() {
		// System.out.println ("In prepareConn().....");
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.66:1521:orcl12c", "AMANDA_TRAINING",
					"Amanda_Training");
		} catch (Exception ex) {
			System.out.println("Exception in preapreConn()..." + ex);
		}
		return con;
	}// prepareConn

	public static void closeConn(Connection con) throws SQLException, IOException {
		// System.out.println ("In closeConn().....");
		if (con != null)
			con.close();
	}// closeConn

	// --------------------------------------------------------------------------
}// class