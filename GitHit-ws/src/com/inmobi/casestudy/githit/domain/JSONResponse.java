package com.inmobi.casestudy.githit.domain;

import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonPropertyOrder({
"status",
"message",
"data"
})
public class JSONResponse {
	
	@JsonProperty("status")
	private int status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object data;
	
	public JSONResponse() {
		
	}
	
	public JSONResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public JSONResponse(int status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			return "";
		}
	}
	

}