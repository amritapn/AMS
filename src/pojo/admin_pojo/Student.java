package pojo.admin_pojo;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Student {
	private int id;
	private double installmentAmount, paymentPending, paid;
	private String name, gender, email, plot, city, state, course, paymentType, installmentDuration, phone, pin,
			status = "Active";
	private Date dob, doj;

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

	public void setInstallmentAmount(double installmentAmount) {
		this.installmentAmount = installmentAmount;
	}

	public double getInstallmentAmount() {
		return installmentAmount;
	}

	public void setPaymentPending(double paymentPending) {
		this.paymentPending = paymentPending;
	}

	public double getPaymentPending() {
		return paymentPending;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public double getPaid() {
		return paid;
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

	public void setCourse(String course) {
		this.course = course;
	}

	public String getCourse() {
		return course;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setInstallmentDuration(String installmentDuration) {
		this.installmentDuration = installmentDuration;
	}

	public String getInstallmentDuration() {
		return installmentDuration;
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
}