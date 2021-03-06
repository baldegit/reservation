package com.balde.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	private String login;
	
	@Column(nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Roles role;
	
	@Column(name = "firstname",nullable = false)
	private String firstName;
	
	@Column(name = "lastname", nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String langue;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(String login, String password, Roles role, String firstName, String lastName, String email,
			String langue) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.langue = langue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}
	
}
