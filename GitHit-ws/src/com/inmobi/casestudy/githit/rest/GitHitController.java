package com.inmobi.casestudy.githit.rest;

import com.inmobi.casestudy.githit.datastore.InMemStoreFactory;
import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.domain.HistoryRecord;
import com.inmobi.casestudy.githit.domain.JSONResponse;
import com.inmobi.casestudy.githit.services.AuthenticationService;
import com.inmobi.casestudy.githit.services.GitHitService;
import com.inmobi.casestudy.githit.utils.GitHitUtils;
import com.codahale.metrics.annotation.Timed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/githit")
@Produces(MediaType.APPLICATION_JSON)
public class GitHitController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(GitHitController.class);
	GitHitService gitHitService;
	AuthenticationService authService;
    public GitHitController() {
    		StoreFactory inMemStoreFactory = new InMemStoreFactory();
    		gitHitService = new GitHitService(inMemStoreFactory);
    		authService = new AuthenticationService(inMemStoreFactory);
    }
    
    @GET
    @Path("/getDetails/{git_handle}")
    @Timed
    public String getDetails(@QueryParam("session_id") String sessionId, @PathParam("git_handle") String gitHandle, @Context HttpServletResponse response) throws Exception {
    		GitHitUtils.enableCORS(response);
    		try {
    			if(authService.authenticateSession(sessionId)) {
    				LOGGER.info("session " + sessionId +" authenticated successfully");
    				return gitHitService.getGitUserDetails(authService.getUserFromSession(sessionId).getEmailId() , gitHandle).toJSONString(); 
    			}else {
    				LOGGER.info("Unauthorized access attempt on session " + sessionId);
    				return new JSONResponse(401, "Unauthorized").toString();
    			}
    		}catch(Exception e) {
    			return new JSONResponse(500, "ServerException").toString();
    		}
    }

    @GET
    @Path("/getHistory")
    @Timed
    public JSONResponse getProduct(@QueryParam("session_id") String sessionId, @Context HttpServletResponse response) throws Exception {
    		GitHitUtils.enableCORS(response);
    		JSONResponse jsonResponse = new JSONResponse();
    		try {
    			if(authService.authenticateSession(sessionId)) {
    				LOGGER.debug("session " + sessionId +" authenticated successfully");
    				jsonResponse.setStatus(200);
    				jsonResponse.setMessage("Data retrieved successfully");
    				List data = gitHitService.getHistory(authService.getUserFromSession(sessionId).getEmailId());
    				data = data == null ? (new ArrayList<HistoryRecord>()) : data;
    				jsonResponse.setData(data);
    				return jsonResponse;
    			}
    			else {
    				LOGGER.info("Unauthorized access attempt on session " + sessionId);
    				return new JSONResponse(401,"Unauthorized access");
    			}
    		}catch(Exception e) {
    			return new JSONResponse(500,"Server Exception");
    		}
    }
 
    @DELETE
    @Path("/deleteHistoryRecord/{id}")
    @Timed
    public JSONResponse deleteHisotryRecord(@QueryParam("session_id") String sessionId, @PathParam("id") Integer historyRecordId, @Context HttpServletResponse response) throws Exception {
    		GitHitUtils.enableCORS(response);
    		try {
    			if(authService.authenticateSession(sessionId)) {
    				LOGGER.debug("session " + sessionId +" authenticated successfully");
    				gitHitService.deleteHistory(historyRecordId);
    				response.setStatus(200);
    				return new JSONResponse(200, "Successfully Deleted");
    			}
    			else {
    				LOGGER.info("Unauthorized access attempt on session " + sessionId);
    				return new JSONResponse(401, "Unauthorized to access this resource");
    			}
    		}catch(Exception e) {
    			return new JSONResponse(500,"Server Exception");
    		}
    }
    
}