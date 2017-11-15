package common.pojo_common;

public class Salary {
	private double basicSalary, grossSalary;
	private int ta, da, hra, epf;

	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public double getBasicSalary() {
		return basicSalary;
	}

	public void setTa(int ta) {
		this.ta = ta;
	}

	public int getTa() {
		return ta;
	}

	public void setDa(int da) {
		this.da = da;
	}

	public int getDa() {
		return da;
	}

	public void setHra(int hra) {
		this.hra = hra;
	}

	public int getHra() {
		return hra;
	}

	public void setEpf(int epf) {
		this.epf = epf;
	}

	public int getEpf() {
		return epf;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public double getGrossSalary() {
		return grossSalary;
	}
}