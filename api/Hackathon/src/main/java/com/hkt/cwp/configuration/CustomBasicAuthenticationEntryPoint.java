///*
// * Copyright (C) 2018 Co-Well Asia. All rights reserved.
// * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//
//package com.hkt.cwp.configuration;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.ELRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
///**
// * It is the entry point of the application. All the requests are intercepted by
// * this class
// * @author CaoTT
// */
//public class CustomBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
//
//	private static final RequestMatcher requestMatcher1 = new ELRequestMatcher(
//            "hasHeader('X-Requested-With','XMLHttpRequest')");
//	
//	@Override
//	public void afterPropertiesSet() throws Exception {
//		setRealmName("HACKATHON");
//		super.afterPropertiesSet();
//	}
//
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException authException) throws IOException, ServletException {
//		
//		if(isPreflight(request)){
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        } else if (isRestRequest(request)) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//            response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
//
//    		PrintWriter writer = response.getWriter();
//    		writer.println("HTTP Status 401 : " + authException.getMessage());
//        } else {
//            super.commence(request, response, authException);
//        }
//		
//		
//		// Authentication failed, send error response.
////		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName() + "");
////
////		PrintWriter writer = response.getWriter();
////		writer.println("HTTP Status 401 : " + authException.getMessage());
//	}
//	
//	 /**
//     * Checks if this is a X-domain pre-flight request.
//     * @param request
//     * @return
//     */
//    private boolean isPreflight(HttpServletRequest request) {
//        return "OPTIONS".equals(request.getMethod());
//    }
//
//    /**
//     * Checks if it is a rest request
//     * @param request
//     * @return
//     */
//    protected boolean isRestRequest(HttpServletRequest request) {
//        return requestMatcher1.matches(request);
//    }
//    
//}
