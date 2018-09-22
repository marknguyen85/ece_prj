package com.hkt.cwp.services;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

public interface TestDetailService {
	ResultBean insertTest(String json) throws MessageListException, Exception;
	HttpStatus getStatus();
}
