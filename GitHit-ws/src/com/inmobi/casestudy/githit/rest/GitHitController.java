package com.inmobi.casestudy.githit.rest;

import com.google.common.base.Optional;
import com.inmobi.casestudy.githit.datastore.InMemProductStore;
import com.inmobi.casestudy.githit.datastore.InMemStoreFactory;
import com.inmobi.casestudy.githit.datastore.StoreFactory;
import com.inmobi.casestudy.githit.domain.Product;
import com.inmobi.casestudy.githit.services.AuthenticationService;
import com.inmobi.casestudy.githit.services.ProductService;
import com.codahale.metrics.annotation.Timed;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class GitHitController {
    
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	ProductService productService;
	AuthenticationService authService;
    public ProductController() {
    		StoreFactory inMemStoreFactory = new InMemStoreFactory();
    		productService = new ProductService(inMemStoreFactory);
    		authService = new AuthenticationService(inMemStoreFactory);
    }

    @GET
    @Path("/getAllProducts")
    @Timed
    public List<Product> getAllProducts(@QueryParam("session_id") String sessionId, @QueryParam("start_index") Integer startIndex,@QueryParam("num_of_products") Integer numberOfProductsToRetrieve, @Context HttpServletResponse response) throws Exception {
    		response.setHeader("Access-Control-Allow-Origin", "*");
    		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    		if(authService.authenticateSession(sessionId)) {
    			LOGGER.debug("session " + sessionId +" authenticated successfully");
    			return productService.getAllProducts(startIndex, numberOfProductsToRetrieve);
    		}else {
    			LOGGER.info("Unauthorized access attempt on session " + sessionId);
    			response.setStatus(401);
    			response.sendError(401, "Unauthorized");
    		}
    		return null;
    }

    @GET
    @Path("/{id}")
    @Timed
    public Product getProduct(@QueryParam("session_id") String sessionId, @PathParam("id") Integer productID, @Context HttpServletResponse response) throws Exception {
    		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    		if(authService.authenticateSession(sessionId)) {
    			LOGGER.debug("session " + sessionId +" authenticated successfully");
    			return productService.getProduct(productID);
    		}
    		else {
    			LOGGER.info("Unauthorized access attempt on session " + sessionId);
    			response.setStatus(401);
    			response.sendError(401, "Unauthorized");
    		}
    		return null;
    }
 
    @PUT
    @Path("/{id}")
    @Timed
    public Product updateProduct(@QueryParam("session_id") String sessionId, @PathParam("id") Integer productID, Product product, @Context HttpServletResponse response) throws Exception {
    		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
    		if(authService.authenticateSession(sessionId) 
    				&& authService.isUserAdmin(sessionId)) {
    			
    			LOGGER.debug("session " + sessionId +" authenticated successfully");
    			return productService.updateProduct(product);
    		}
    		else {
    			LOGGER.info("Unauthorized access attempt on session " + sessionId);
    			response.setStatus(401);
    			response.sendError(401, "Unauthorized");
    		}
    		return null;
    }
    
}