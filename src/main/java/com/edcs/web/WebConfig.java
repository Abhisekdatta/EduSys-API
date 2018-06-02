package com.edcs.web;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.edcs.resources.ObjectMapperFactory;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}
	
    @Bean
    public Jackson2JsonObjectMapper jackson2JsonObjectMapper() {
        return ObjectMapperFactory.getMapper();
    }
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE")
                .allowedOrigins("*");
    }
    
}
