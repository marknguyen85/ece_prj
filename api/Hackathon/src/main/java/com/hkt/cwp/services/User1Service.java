package com.hkt.cwp.services;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;

/**
 * 
 * @author thuan
 *
 */
public interface User1Service {
	ResultBean getUserInfo(String user_id) throws MessageListException, Exception;
	ResultBean getListTest(String user_id) throws MessageListException, Exception;
	ResultBean getTestDetail (String test_id)  throws MessageListException, Exception;
	ResultBean getAbility (String user_id) throws MessageListException, Exception;
	ResultBean getKPI (String kpi_id) throws MessageListException, Exception;
	ResultBean getKPIByTime(String user_id,String kpi_id) throws MessageListException, Exception;
	ResultBean getSkill(String user_id) throws MessageListException, Exception;
	HttpStatus getStatus();
}
