package common.pojo_common;

public class PasswordBean {
	private String oldPass, newPass;
	private int id;

	public String getOldPass() {
		return oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
}