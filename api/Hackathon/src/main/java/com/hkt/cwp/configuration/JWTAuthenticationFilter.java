///*
// * Copyright (C) 2018 Co-Well Asia. All rights reserved.
// * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
// */
//package com.hkt.cwp.configuration;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.GenericFilterBean;
//
//import com.google.gson.JsonObject;
//import com.hkt.cwp.Utils.BundleUtils;
//import com.hkt.cwp.Utils.Constants;
//
//import io.jsonwebtoken.ExpiredJwtException;
//
///**
// * @author CaoTT
// *
// */
//public class JWTAuthenticationFilter extends GenericFilterBean {
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
//	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
//	 */
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		try {
//			Authentication authentication = TokenAuthentication.getAuthentication((HttpServletRequest) request);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		} catch (ExpiredJwtException e) {
//			returnTokenExpire(response);
//			return;
//		} catch (Exception e) {
//			returnError(response);
//			e.printStackTrace();
//			return;
//		}
//		chain.doFilter(request, response);
//	}
//
//	/**
//	 * Handler token expire
//	 * 
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	public void returnTokenExpire(ServletResponse response) throws IOException, ServletException {
//		HttpServletResponse hsr = (HttpServletResponse) response;
//	    hsr.setStatus(408);
//	    hsr.setContentType("application/json");
//	    hsr.setCharacterEncoding( "UTF-8");
//		PrintWriter writer = hsr.getWriter();
//		JsonObject jsonObject = new JsonObject();
//		jsonObject.addProperty("message", BundleUtils.getString(Constants.ERR_ID_REQUEST_TIMEOUT));
//		jsonObject.addProperty("code", Constants.ERR_ID_REQUEST_TIMEOUT);
//		writer.println(jsonObject);
//		writer.close();
//	}
//	
//	/**
//	 * Handle others exception
//	 * 
//	 * @param response
//	 * @throws IOException
//	 * @throws ServletException
//	 */
//	public void returnError(ServletResponse response) throws IOException, ServletException {
//		HttpServletResponse hsr = (HttpServletResponse) response;
//		hsr.setStatus(403);
//	    hsr.setContentType("application/json");
//	    hsr.setCharacterEncoding( "UTF-8");
//		PrintWriter writer = hsr.getWriter();
//		JsonObject jsonObject = new JsonObject();
//		jsonObject.addProperty("message", BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
//		jsonObject.addProperty("code", Constants.ERR_ID_INTERNAL_SERVER_ERROR);
//		jsonObject.addProperty("result", Constants.RESULT_FAIL);
//		writer.println(jsonObject);
//		writer.close();
//	}
//	
//}
