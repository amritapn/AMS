package common.pojo_common;

import java.util.*;

public class JBean {
	ArrayList privilegesList;
	ArrayList loginCredential = new ArrayList();
	ArrayList updatedEmpDetails = new ArrayList();
	int emp_id, id;
	String errorMessage, message;
	String email, password;
	String searchEmpId, searchEmpName, searchEmpDesignation, searchEmpStatus;
	String studentName, studentStatus, studentPending;
	String studentId;
	String studentCourse;
	String empName, empEmail, empPhone, empPlot, empCity, empState, empPin, empDesignation, empStatus, empRole;
	Date empDob, empDoj;

	public void setEmail(String email) {

		this.email = email;

	}

	public void setPassword(String password) {

		this.password = password;
	}

	public void setId(int emp_id) {
		this.emp_id = emp_id;
	}

	public int getId() {
		return emp_id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	// -------------- For Login ----------------------------

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	public Date getEmpDob() {
		return empDob;
	}

	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}

	public String getEmpPhone() {
		return empPhone;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpPlot(String empPlot) {
		this.empPlot = empPlot;
	}

	public String getEmpPlot() {
		return empPlot;
	}

	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}

	public String getEmpCity() {
		return empCity;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}

	public String getEmpState() {
		return empState;
	}

	public void setEmpPin(String empPin) {
		this.empPin = empPin;
	}

	public String getEmpPin() {
		return empPin;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public String getEmpDesignation() {
		return empDesignation;
	}

	public void setEmpDoj(Date empDoj) {
		this.empDoj = empDoj;
	}

	public Date getEmpDoj() {
		return empDoj;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}

	public String getEmpRole() {
		return empRole;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setLoginCredential(ArrayList loginCredential) {
		this.loginCredential = loginCredential;

	}

	public ArrayList getLoginCredential() {

		return loginCredential;

	}

	// -------------- For Login ----------------------------

	// -------------- for ViewEmployee ---------------------

	public void setSearchEmpId(String searchEmpId) {
		this.searchEmpId = searchEmpId;
	}

	public String getSearchEmpId() {
		return searchEmpId;
	}

	public void setSearchEmpName(String searchEmpName) {
		this.searchEmpName = searchEmpName;
	}

	public String getSearchEmpName() {
		return searchEmpName;
	}

	public void setSearchEmpDesignation(String searchEmpDesignation) {
		this.searchEmpDesignation = searchEmpDesignation;
	}

	public String getSearchEmpDesignation() {
		return searchEmpDesignation;
	}

	public void setSearchEmpStatus(String searchEmpStatus) {
		this.searchEmpStatus = searchEmpStatus;
	}

	public String getSearchEmpStatus() {
		return searchEmpStatus;
	}

	// ------------for viewEmployee----------------------

	// ------------ for viewStudent-----------------------

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentPending(String studentPending) {
		this.studentPending = studentPending;
	}

	public String getStudentPending() {
		return studentPending;
	}

	public void setStudentCourse(String studentCourse) {
		this.studentCourse = studentCourse;
	}

	public String getStudentCourse() {
		return studentCourse;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	// ------------ for viewStudent-----------------------

	// ------------ for editing employees ----------------

	public void setUpdatedEmpDetails(ArrayList updatedEmpDetails) {
		this.updatedEmpDetails = updatedEmpDetails;
	}

	public ArrayList getUpdatedEmpDetails() {
		return updatedEmpDetails;
	}

	public void setEmpId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return id;
	}

	// ------------editing employees---------------------

}
