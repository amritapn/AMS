package pojo.system_admin_pojo;

import java.util.Date;

import common.pojo_common.Salary;

import java.text.SimpleDateFormat;

public class Employee {
	private int id, leaveOpted;
	private String status = "Active", phone, pin, name, gender, designation, email, plot, city, state, role, password;
	private Date dob, doj;
	private Salary salary;
	private AccessPrivilege privilege;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setLeaveOpted(int leaveOpted) {
		this.leaveOpted = leaveOpted;
	}

	public int getLeaveOpted() {
		return leaveOpted;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getPin() {
		return pin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public String getPlot() {
		return plot;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setDob(String dob) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.dob = format.parse(dob);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getDob() {
		return dob;
	}

	public void setDoj(String doj) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.doj = format.parse(doj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getDoj() {
		return doj;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Salary getSalary() {
		return salary;
	}

	public void setAccessPrivilege(AccessPrivilege privilege) {
		this.privilege = privilege;
	}

	public AccessPrivilege getPrivilege() {
		return privilege;
	}
}
