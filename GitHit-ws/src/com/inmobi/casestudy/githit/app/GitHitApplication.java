package com.inmobi.casestudy.githit.app;

import java.util.ArrayList;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.inmobi.casestudy.githit.caching.CacheManager;
import com.inmobi.casestudy.githit.datastore.DataStoreFactory;
import com.inmobi.casestudy.githit.datastore.InMemStoreFactory;
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
		
		enableCORSForApplication(env);
		setupApplicationCache(env);
		DataStoreFactory dataStoreFactory = getDataStore(env);
		final GitHitController gitHitController = new GitHitController(dataStoreFactory);
		final LoginController loginController = new LoginController(dataStoreFactory);
		env.jersey().register(gitHitController);
		env.jersey().register(loginController);
	}
	
	private void setupApplicationCache(Environment env) {
		CacheManager.createCache("LOGGED_IN_USERS", User.class);
		CacheManager.createCache("SEARCH_HISTORY", ArrayList.class);
	}
	
	private void enableCORSForApplication(Environment env) {
		final FilterRegistration.Dynamic cors =
		        env.servlets().addFilter("CORS", CrossOriginFilter.class);

		cors.setInitParameter("allowedOrigins", "*");
		cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
		cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");
		cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "*");
	}
	
	private DataStoreFactory getDataStore(Environment env) {
		return new InMemStoreFactory();
	}
	
}
