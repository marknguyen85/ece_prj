package com.hkt.cwp.services;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

public interface SkillService {
	ResultBean getListKPI(String kpi_id) throws MessageListException , Exception;
	ResultBean getListKPIByTime(String user_id,String kpi_id) throws MessageListException, Exception;
	
	HttpStatus getStatus();
}
