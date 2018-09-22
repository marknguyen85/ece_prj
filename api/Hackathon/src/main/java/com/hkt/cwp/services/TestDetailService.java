package com.hkt.cwp.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

public interface TestDetailService {
	ResultBean insertTest(HttpServletRequest request) throws MessageListException, Exception;
	ResultBean updateTest(HttpServletRequest request) throws MessageListException, Exception;
	HttpStatus getStatus();
}
