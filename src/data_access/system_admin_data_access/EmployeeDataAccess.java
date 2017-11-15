package data_access.system_admin_data_access;

import java.util.*;

import common.common_data_access.DBConnect;
import common.pojo_common.Salary;
import pojo.system_admin_pojo.Employee;

import java.sql.*;
import java.text.SimpleDateFormat;

public class EmployeeDataAccess {
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

	public static int add(Employee employee) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			java.sql.Date dob = new java.sql.Date(employee.getDob().getTime());
			java.sql.Date doj = new java.sql.Date(employee.getDoj().getTime());
			statement = connection.prepareStatement(
					"insert into employee_account values(emp_id.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getGender());
			statement.setDate(3, dob);
			statement.setString(4, employee.getPhone());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getPlot());
			statement.setString(7, employee.getCity());
			statement.setString(8, employee.getState());
			statement.setString(9, employee.getPin());
			statement.setString(10, employee.getDesignation());
			statement.setDate(11, doj);
			statement.setString(12, employee.getStatus());
			statement.setString(13, employee.getPassword());
			statement.setString(14, employee.getRole());
			queryStatus = statement.executeUpdate();
			if (queryStatus > 0) {
				int id = 0;
				String role = "";
				statement = connection.prepareStatement("select emp_id, role from employee_account where email = ?");
				statement.setString(1, employee.getEmail());
				ResultSet dataHolder = statement.executeQuery();
				if (dataHolder.next()) {
					id = dataHolder.getInt(1);
					role = dataHolder.getString(2);
					String sysAdmin = "false", admin = "false", hr = "false", accountManager = "false", user = "false";
					statement = connection
							.prepareStatement("insert into employeeaccessprivilege values(?, ?, ?, ?, ?, ?)");
					statement.setInt(1, id);
					statement.setString(2, admin);
					statement.setString(3, hr);
					statement.setString(4, accountManager);
					statement.setString(5, sysAdmin);
					statement.setString(6, user);
					queryStatus = statement.executeUpdate();
				}
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

	public static List<Employee> viewAll() {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement(
					"select emp_id, name, designation, phone, email, doj, status from employee_account order by emp_id");
			ResultSet dataHolder = statement.executeQuery();
			while (dataHolder.next()) {
				Employee employee = new Employee();
				employee.setId(dataHolder.getInt(1));
				employee.setName(dataHolder.getString(2));
				employee.setDesignation(dataHolder.getString(3));
				employee.setPhone(dataHolder.getString(4));
				employee.setEmail(dataHolder.getString(5));
				java.sql.Date date = dataHolder.getDate(6);
				java.util.Date javaDate = (java.util.Date) date;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String doj = dateFormat.format(javaDate);
				employee.setDob(doj);
				employee.setStatus(dataHolder.getString(7));

				statement = connection.prepareStatement(
						"select basic_salary, ta, da, hra, epf, gross_salary from employee_salary where emp_id = ?");
				statement.setInt(1, employee.getId());
				ResultSet salaryHolder = statement.executeQuery();
				if (salaryHolder.next()) {
					Salary salary = new Salary();
					salary.setBasicSalary(salaryHolder.getDouble(1));
					salary.setTa(salaryHolder.getInt(2));
					salary.setDa(salaryHolder.getInt(3));
					salary.setHra(salaryHolder.getInt(4));
					salary.setEpf(salaryHolder.getInt(5));
					salary.setGrossSalary(salaryHolder.getDouble(6));
					employee.setSalary(salary);
				}

				employeeList.add(employee);
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
		return employeeList;
	}

	public static Employee getEmployeeById(int id) {
		Employee employee = new Employee();
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("select * from employee_account where emp_id = ?");
			statement.setInt(1, id);
			ResultSet dataHolder = statement.executeQuery();
			if (dataHolder.next()) {
				employee.setId(dataHolder.getInt(1));
				employee.setName(dataHolder.getString(2));
				employee.setGender(dataHolder.getString(3));
				java.sql.Date date = dataHolder.getDate(4);
				java.util.Date javaDate = (java.util.Date) date;
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				String dob = dateFormat.format(javaDate);
				employee.setDob(dob);
				employee.setPhone(dataHolder.getString(5));
				employee.setEmail(dataHolder.getString(6));
				employee.setPlot(dataHolder.getString(7));
				employee.setCity(dataHolder.getString(8));
				employee.setState(dataHolder.getString(9));
				employee.setPin(dataHolder.getString(10));
				employee.setDesignation(dataHolder.getString(11));
				date = dataHolder.getDate(12);
				javaDate = (java.util.Date) date;
				String doj = dateFormat.format(javaDate);
				employee.setDoj(doj);
				employee.setStatus(dataHolder.getString(13));
				employee.setPassword(dataHolder.getString(14));
				employee.setRole(dataHolder.getString(15));

				statement = connection.prepareStatement(
						"select basic_salary, ta, da, hra, epf, gross_salary from employeesalary where emp_id = ?");
				statement.setInt(1, id);
				ResultSet salaryHolder = statement.executeQuery();
				if (salaryHolder.next()) {
					Salary salary = new Salary();
					salary.setBasicSalary(salaryHolder.getDouble(1));
					salary.setTa(salaryHolder.getInt(2));
					salary.setDa(salaryHolder.getInt(3));
					salary.setHra(salaryHolder.getInt(4));
					salary.setEpf(salaryHolder.getInt(5));
					salary.setGrossSalary(salaryHolder.getDouble(6));
					employee.setSalary(salary);
				}
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
		return employee;
	}

	public static int edit(Employee employee) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			java.sql.Date dob = new java.sql.Date(employee.getDob().getTime());
			java.sql.Date doj = new java.sql.Date(employee.getDoj().getTime());
			statement = connection.prepareStatement(
					"update employee_account set name = ?, gender = ?, dob = ?, phone = ?, email = ?, plot = ?, city = ?, state = ?, pin = ?, designation = ?, doj = ?, status = ? where emp_id = ?");
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getGender());
			statement.setDate(3, dob);
			statement.setString(4, employee.getPhone());
			statement.setString(5, employee.getEmail());
			statement.setString(6, employee.getPlot());
			statement.setString(7, employee.getCity());
			statement.setString(8, employee.getState());
			statement.setString(9, employee.getPin());
			statement.setString(10, employee.getDesignation());
			statement.setDate(11, doj);
			statement.setString(12, employee.getStatus());
			statement.setInt(13, employee.getId());
			queryStatus = statement.executeUpdate();
			if (queryStatus > 0) {
				statement = connection.prepareStatement(
						"update employee_salary set basic_salary = ?, ta = ?, da = ?, hra = ?, epf = ?, gross_salary = ? where emp_id = ?");
				statement.setDouble(1, employee.getSalary().getBasicSalary());
				statement.setInt(2, employee.getSalary().getTa());
				statement.setInt(3, employee.getSalary().getDa());
				statement.setInt(4, employee.getSalary().getHra());
				statement.setInt(5, employee.getSalary().getEpf());
				statement.setDouble(6, employee.getSalary().getGrossSalary());
				statement.setInt(7, employee.getId());
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

	public static int changePassword(int id, String password) {
		int queryStatus = 0;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DBConnect.prepareConn();
			statement = connection.prepareStatement("update employee_account set password = ? where emp_id =?");
			statement.setString(1, password);
			statement.setInt(2, id);
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

	public static double calculateGrossSalary(double basicSalary, int ta, int da, int hra, int epf) {
		double taAmount = basicSalary * (ta / 100);
		double daAmount = basicSalary * (da / 100);
		double hraAmount = basicSalary * (hra / 100);
		double epfAmount = basicSalary * (epf / 100);
		double grossSalary = (basicSalary + ta + da + hra) - epf;
		return grossSalary;
	}

}