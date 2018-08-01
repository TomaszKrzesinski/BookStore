package pl.jstk.entity;

import pl.jstk.enumerations.BookStatus;
import pl.jstk.enumerations.UserRole;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(nullable = false, length = 50)
	private String userName;
	@Column(nullable = false, length = 200)
	private String password;
	@Column(nullable = false)
	private boolean enabled;
	@Enumerated(EnumType.STRING)
	private UserRole role;

	// for hibernate
	protected UserEntity() {
	}

	public UserEntity(Long id, String user, String password, boolean enabled, UserRole role) {
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
