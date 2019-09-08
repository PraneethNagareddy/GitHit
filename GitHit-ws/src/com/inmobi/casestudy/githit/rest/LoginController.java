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
import com.inmobi.casestudy.githit.datastore.DataStoreFactory;
import com.inmobi.casestudy.githit.domain.JSONResponse;
import com.inmobi.casestudy.githit.domain.LoginRequest;
import com.inmobi.casestudy.githit.domain.LoginResponse;
import com.inmobi.casestudy.githit.services.AuthenticationService;
import com.inmobi.casestudy.githit.utils.GitHitUtils;

@Path("/authenticate")
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
	AuthenticationService authService;
    public LoginController(DataStoreFactory dataStoreFactory) {
    		authService = new AuthenticationService(dataStoreFactory);
    }

	@POST
	@Path("/login")
	@Timed
	@Consumes(MediaType.APPLICATION_JSON)
    public JSONResponse logUserIn(LoginRequest request, @Context HttpServletResponse response) throws Exception {
		GitHitUtils.enableCORS(response);
		try {
			String sessionId = authService.logUserIn(request.getEmailId(), request.getPassword());
    			if("".equals(sessionId)) {
    				LOGGER.info("Unauthorized access attemot on id"+request.getEmailId());
    				return new JSONResponse(401,"Unauthorized access");
    			}
    			LoginResponse loginresponse = new LoginResponse();
    			loginresponse.setSessionId(sessionId);
    			return new JSONResponse(200, "Successfully logged in", loginresponse);
		}catch(Exception e) {
			return new JSONResponse(500, "Server Error");
		}
    }
	
	
}
