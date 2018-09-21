package com.hkt.cwp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.services.TestService;


/**
 * 
 * @author thuan
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	 @Autowired
	 private TestService testService;
	
	
	 @RequestMapping(value = "/test1", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getAllTest(){
		 ResultBean resultBean = null;
		 resultBean = testService.getAllTest();
		 return new ResponseEntity<>(resultBean, HttpStatus.OK);
	 }
	   
}
