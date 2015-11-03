package com.myretail;

import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.myretail.config.MyRetailConfig;

/**
 * Entry point into the app.
 * Wires up the configuration sources and runs the app.
 * 
 * @author fishej2
 *
 */

//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan ... I'm using the three of them explicity to exclude DataSourceAutoConfig.
@Configuration 
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class}) 
@ComponentScan
public class MyRetailApplication extends SpringBootServletInitializer{

	private static Logger LOG = LoggerFactory.getLogger(MyRetailApplication.class);
	
	public MyRetailApplication() {
		
	}
	
	/**
	 * Keep this for local dev when using the embedded Container.
	 * @param args
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(MyRetailApplication.class);	
        application.setSources(defineConfigSources());
                       
        //Enable/Disable container restart when saving files.
    	System.setProperty("spring.devtools.restart.enabled", "false");
    	
    	ApplicationContext applicationContext = application.run(args);
    	LOG.info("application context :: {}",  applicationContext.getClass());		
    }
    
    /**
     * Defines the source of the config files.
     * @return
     */
	private static HashSet<Object> defineConfigSources() {
		HashSet<Object> sources = new HashSet<Object>();
		sources.add(MyRetailConfig.class);
		return sources;
	}

}
