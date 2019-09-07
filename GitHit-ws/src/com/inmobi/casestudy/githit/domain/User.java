package com.inmobi.casestudy.githit.domain;

import java.util.Date;

public class User {
	private String name;
	private String emailId;
	private Date dateOfBirth;
	private String encryptedPassword;
	private boolean isAdmin;
	
	public User(String name, String emailId, Date dateOfBirth, String encryptedPassword, boolean isAdmin) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
		this.encryptedPassword = encryptedPassword;
		this.isAdmin = isAdmin;
	}
	public User(String name, String emailId, String encryptedPassword) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.encryptedPassword = encryptedPassword;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
