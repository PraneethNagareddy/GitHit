package com.inmobi.casestudy.githit.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"session_id"
})
public class LoginResponse {

	@JsonProperty("session_id")
	private String sessionId;

	public LoginResponse() {
		
	}

	@JsonProperty("session_id")
	public String getSessionId() {
		return sessionId;
	}

	@JsonProperty("session_id")
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}