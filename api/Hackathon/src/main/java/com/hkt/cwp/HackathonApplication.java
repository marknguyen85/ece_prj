package com.hkt.cwp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.hkt.cwp")
@EnableAutoConfiguration
public class HackathonApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}
	
	@Bean
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet ds = new DispatcherServlet();
        ds.setThrowExceptionIfNoHandlerFound(true);
        return ds;
    }
}
