package com.myretail.config;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.configuration.AbstractConfiguration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.myretail.service.ProductService;
import com.myretail.service.ValidationService;

@MapperScan("com.myretail.persistence")
public class MyRetailConfig extends AbstractConfiguration {

	private static Logger LOG = LoggerFactory.getLogger(MyRetailConfig.class);
	
	public MyRetailConfig() {
	}
	
	@Autowired
	private Environment env;
	
	@Bean(name="productService")
	public ProductService productService() {
		return new ProductService();
	}
	
	@Bean(name="validationService")
	public ValidationService validationService() {
		return new ValidationService();
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
		builder.indentOutput(true);
		builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		builder.failOnEmptyBeans(false);
		return builder;
	}
	
	@Bean
	public DriverManagerDataSource datasource() {
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/myretail");
		datasource.setUsername("root");
		datasource.setPassword("admin");
		return datasource;
	}
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(datasource());
	}

	 
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource());
        sessionFactory.setTypeAliasesPackage("com.myretail.model");
        return sessionFactory;
    }
    
    @Bean(name="simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver r =
              new SimpleMappingExceptionResolver();

        Properties mappings = new Properties();
        mappings.setProperty("RuntimeException", "runtimeException");

        r.setExceptionMappings(mappings);  // None by default
        r.setDefaultErrorView("error");    // No default
        r.setExceptionAttribute("exception");     // Default is "exception"
        r.setWarnLogCategory("example.MvcLogger");     // No default
        return r;
    }   
	
	@PostConstruct
	private void init() {
		LOG.info("Initialized myRetail config.");
		if (env instanceof ConfigurableEnvironment) {
			LOG.info("We have an environment that is ConfigurableEnvironment.");
			LOG.info("Property :: secure-tokens::" + env.getProperty("application-state.use-secure-tokens"));
		} else {
			LOG.info("Not a configurable environment.");
		}
	}
	
	@Override
	public Object getProperty(String key) {
		return null;
	}
	
	@Override
	public boolean containsKey(String key) {
		return getProperty(key) != null;
	}

	@Override
	public Iterator<String> getKeys() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return getKeys().hasNext();
	}
	
	@Override
	protected void addPropertyDirect(String key, Object value) {
		
	}


}
