package pojo.account_manager_pojo;

import common.pojo_common.*;

public class EmployeeSalary {
	private int empId;
	private String empName;
	private Salary salary;

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setSalary(Salary salary) {
		this.salary = salary;
	}

	public Salary getSalary() {
		return salary;
	}
}
