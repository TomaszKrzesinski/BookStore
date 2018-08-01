package pl.jstk.to;

import pl.jstk.enumerations.UserRole;

public class UserTo {
	private Long id;
	private String userName;
	private String password;
	private boolean enabled;
	private UserRole role;

	public UserTo() {
	}

	public UserTo(Long id, String user, String password, boolean enabled, UserRole role) {
		this.id = id;
		this.userName = user;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String user) {
		this.userName = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() { return role; }

	public void setRole(UserRole role) {this.role = role; }

	public boolean isEnabled() { return enabled;}

	public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
