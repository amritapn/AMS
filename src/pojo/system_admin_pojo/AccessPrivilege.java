package pojo.system_admin_pojo;

public class AccessPrivilege {
	private boolean admin = false;
	private boolean hr = false;
	private boolean accountManager = false;
	private boolean systemAdmin = false;

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isHr() {
		return hr;
	}

	public void setHr(boolean hr) {
		this.hr = hr;
	}

	public boolean isAccountManager() {
		return accountManager;
	}

	public void setAccountManager(boolean accountManager) {
		this.accountManager = accountManager;
	}

	public boolean isSystemAdmin() {
		return systemAdmin;
	}

	public void setSystemAdmin(boolean systemAdmin) {
		this.systemAdmin = systemAdmin;
	}

}