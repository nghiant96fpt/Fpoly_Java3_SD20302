package com.fpoly.java3.entities;

import java.util.Date;

public class User {
	private String email;
	private String password;
	private String name;
	private boolean gender;
	private String phone;
	private Date birthday;
	private int role;

	public User() {
	}

	public User(String email, String password, String name, boolean gender, String phone, Date birthday, int role) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.birthday = birthday;
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}
