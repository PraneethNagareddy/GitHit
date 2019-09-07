package com.inmobi.casestudy.githit.rest;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;
import com.inmobi.casestudy.githit.datastore.InMemStoreFactory;
import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.domain.LoginRequest;
import com.inmobi.casestudy.githit.domain.LoginResponse;
import com.inmobi.casestudy.githit.services.AuthenticationService;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	AuthenticationService authService;
    public LoginController() {
    		StoreFactory inMemStoreFactory = new InMemStoreFactory();
    		authService = new AuthenticationService(inMemStoreFactory);
    }
	
	@POST
	@Path("/login")
	@Timed
	@Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse logUserIn(LoginRequest request, @Context HttpServletResponse response) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		String sessionId = authService.logUser(request.getEmailId(), request.getPassword());
    		if("".equals(sessionId)) {
    			response.setStatus(401);
    			response.sendError(401, "Unauthorized");
    			LOGGER.info("Unauthorized access attemot on id"+request.getEmailId());
    		}
		LoginResponse loginresponse = new LoginResponse();
		loginresponse.setSessionId(sessionId);
    		return loginresponse;
    }
	
	
}
