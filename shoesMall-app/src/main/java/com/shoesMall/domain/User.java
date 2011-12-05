package com.shoesMall.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends SupperEntity {
	private static final long serialVersionUID = 1L;
	private String email;
	private String name;
	private String password;
	private String confirmPassword;
	private int status;
	private List<Role> roles;
	protected Long id;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USERS")
	@SequenceGenerator(name = "SEQ_USERS", sequenceName = "SEQ_USERS", allocationSize = 1)
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User() {
	}

	public User(String email, String name, String password, int status, List<Role> roles) {
		super();
		this.email = email;
		this.name = name;
		this.password = password;
		this.status = status;
		this.roles = roles;
	}

	@NotEmpty(message = "不能为空")
	@Length(min = 6, message = "不能少于6位")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Email(message = "不合法邮箱")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Transient
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "USERS_ROLE", joinColumns = @JoinColumn(name = "USERNAME", referencedColumnName = "NAME"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
	// @Cascade(CascadeType.ALL)
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}