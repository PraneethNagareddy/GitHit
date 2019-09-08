package com.inmobi.casestudy.githit.app;

import java.util.ArrayList;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.inmobi.casestudy.githit.caching.CacheManager;
import com.inmobi.casestudy.githit.domain.User;
import com.inmobi.casestudy.githit.rest.GitHitController;
import com.inmobi.casestudy.githit.rest.LoginController;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class GitHitApplication extends Application<Configuration>{

	public static void main(String[] args) throws Exception {
		String[] arg = new String[] {"server"};
        new GitHitApplication().run(arg);
    }
	
	@Override
	public void run(Configuration configuration, Environment env) throws Exception {
		final FilterRegistration.Dynamic cors =
		        env.servlets().addFilter("CORS", CrossOriginFilter.class);

		    // Configure CORS parameters
		    cors.setInitParameter("allowedOrigins", "*");
		    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

		    // Add URL mapping
		    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
		CacheManager.createCache("LOGGED_IN_USERS", User.class);
		CacheManager.createCache("SEARCH_HISTORY", ArrayList.class);
		final GitHitController gitHitController = new GitHitController();
		final LoginController loginController = new LoginController();
		env.jersey().register(gitHitController);
		env.jersey().register(loginController);
	}
	
}
