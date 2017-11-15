package pojo.admin_pojo;

public class Course {
	private int id;
	private String name, duration;
	private double fees;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public double getFees() {
		return fees;
	}
}
