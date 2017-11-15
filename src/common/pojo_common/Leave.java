package common.pojo_common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Leave {
	private String name, designation, type;
	private int id, days;
	private java.util.Date startDate, endDate;

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public int getDays() {
		return days;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setStartDate(String startDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.startDate = format.parse(startDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setEndDate(String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			this.endDate = format.parse(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Date getEndDate() {
		return endDate;
	}
}