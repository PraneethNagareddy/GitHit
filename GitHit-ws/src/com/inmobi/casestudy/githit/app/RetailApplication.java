package com.inmobi.casestudy.githit.app;

import com.inmobi.casestudy.githit.rest.LoginController;
import com.inmobi.casestudy.githit.rest.ProductController;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;

public class RetailApplication extends Application<Configuration>{

	public static void main(String[] args) throws Exception {
		String[] arg = new String[] {"server"};
        new RetailApplication().run(arg);
    }
	
	@Override
	public void run(Configuration configuration, Environment env) throws Exception {
		final ProductController productController = new ProductController();
		final LoginController loginController = new LoginController();
		env.jersey().register(productController);
		env.jersey().register(loginController);
	}
	
}
