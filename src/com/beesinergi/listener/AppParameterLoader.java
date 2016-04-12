package com.beesinergi.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.beesinergi.service.AppParameterService;
import com.beesinergi.util.LoaderFactory;

public class AppParameterLoader implements ServletContextListener  {

	public void contextDestroyed(ServletContextEvent arg0) {}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext=servletContextEvent.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		initializeAppParameter(ctx);
	}
	
	private void initializeAppParameter(WebApplicationContext ctx) {
		AppParameterService appParameterService=(AppParameterService)ctx.getBean("appParameterService");
		LoaderFactory.initializeAppParameter(appParameterService);
	}
	
}
	
	
	
	