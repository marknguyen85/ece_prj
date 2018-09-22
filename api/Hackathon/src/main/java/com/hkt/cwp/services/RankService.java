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
public interface RankService {

	public ResultBean searchEmp(String key, String page, String skillIdStr) throws MessageListException, Exception;
	
	HttpStatus getStatus();
}
