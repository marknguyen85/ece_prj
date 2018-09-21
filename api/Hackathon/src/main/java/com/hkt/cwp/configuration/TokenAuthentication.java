/**
 * 
 */
package com.hkt.cwp.configuration;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.models.Employee;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

/**
 * @author CaoTT
 *
 */
public class TokenAuthentication {

	/**
	 * Create token
	 * 
	 * @param authorizeData
	 * @return
	 */
	public static String addAuthentication(Employee user) {
		String id = String.valueOf(user.getId());
		String name = user.getName();
		String userName = user.getUsername();
		String password = user.getPassword();
		String code = user.getCode();

		String JWT = Jwts.builder().claim(Constants.USER_ID, id).claim(Constants.USER_NAME, userName)
				.claim(Constants.PASSWORD, password).claim(Constants.CODE, code).claim(Constants.NAME, name)
				.setExpiration(new Date(System.currentTimeMillis() + Constants.EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, Constants.SECRET).compact();
		return JWT;
	}
	
	/**
	 * Parse token
	 * 
	 * @param request
	 * @return
	 * @throws ExpiredJwtException
	 * @throws Exception
	 */
	public static Authentication getAuthentication(HttpServletRequest request) throws ExpiredJwtException, Exception {
		String token = request.getHeader(Constants.HEADER_STRING);
		if (token != null && !token.trim().isEmpty()) {
			// parse the token.
			try {
				Claims claims = Jwts.parser().setSigningKey(Constants.SECRET).parseClaimsJws(token).getBody();
				String id = (String)claims.get(Constants.USER_ID);
				String userName = (String)claims.get(Constants.USER_NAME);
				String name = (String)claims.get(Constants.NAME);
				String password = (String)claims.get(Constants.PASSWORD);
				String code = (String)claims.get(Constants.CODE);
				
				if (id != null && userName != null && password != null && code != null) {
					Employee authorizeData = new Employee();
					authorizeData.setId(Integer.parseInt(id));
					authorizeData.setName(name);
					authorizeData.setUsername(userName);
					authorizeData.setPassword(password);
					authorizeData.setCode(code);
					request.setAttribute(Constants.USER, authorizeData);
					return new UsernamePasswordAuthenticationToken(id, null, new ArrayList<>());
				} else {
					return null;
				}
			} catch (SignatureException e) {
				return null;
			} 
		}
		return null;
	}
}
