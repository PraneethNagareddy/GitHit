package com.inmobi.casestudy.githit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"email_id",
"password"
})
public class LoginRequest {

	@JsonProperty("email_id")
	private String emailId;
	@JsonProperty("password")
	private String password;

	public LoginRequest() {
		
	}

	@JsonProperty("email_id")
	public String getEmailId() {
		return emailId;
	}

	@JsonProperty("email_id")
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@JsonProperty("password")
	public String getPassword() {
	return password;
	}

	@JsonProperty("password")
	public void setPassword(String password) {
	this.password = password;
	}

}
