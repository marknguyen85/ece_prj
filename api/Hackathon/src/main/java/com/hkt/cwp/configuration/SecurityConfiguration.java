///*
// * Copyright (C) 2018 Co-Well Asia. All rights reserved.
// * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//
//package com.hkt.cwp.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
///**
// * It is the entry point of the application. All the requests are intercepted by
// * this class
// * 
// * @author CaoTT
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
////		http.csrf().disable().authorizeRequests()
////  		.antMatchers(HttpMethod.POST, "/api/user/authorize")
////          .permitAll()
////          .anyRequest().authenticated()
////          .and()
////          .addFilterBefore(new JWTAuthenticationFilter(),
////                  UsernamePasswordAuthenticationFilter.class);
//		
//		http.csrf().disable();
//	}
//
////	@Bean
////	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
////		return new CustomBasicAuthenticationEntryPoint();
////	}
////
////	/* To allow Pre-flight [OPTIONS] request from browser */
////	@Override
////	public void configure(WebSecurity web) throws Exception {
////	}
//	
//}
