/**
 * 
 */
package com.hkt.cwp.services;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

/**
 * @author CaoTT
 *
 */
public interface LoginService {

	ResultBean login(String userName, String password) throws MessageListException , Exception;
	HttpStatus getStatus();
}
