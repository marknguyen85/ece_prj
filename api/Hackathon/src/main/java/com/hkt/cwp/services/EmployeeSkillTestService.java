package com.hkt.cwp.services;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

public interface EmployeeSkillTestService {
	ResultBean getListTest(String user_id) throws MessageListException, Exception;
	HttpStatus getStatus();
}
