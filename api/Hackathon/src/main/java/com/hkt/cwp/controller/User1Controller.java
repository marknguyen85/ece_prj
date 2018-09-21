package com.hkt.cwp.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/**
 * 
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.services.User1Service;

@RestController
@RequestMapping(Constants.USER_PATH)
public class User1Controller {
private ResultBean resultBean;
	
	@Autowired
	private User1Service user1Service;
	
	 @RequestMapping(value = "/getinfo", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getUserInfo(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
	        String user_id = request.getParameter("user_id");
	        try {
	            resultBean = user1Service.getUserInfo(user_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value = "/listtest", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getListTest(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
	        String user_id = request.getParameter("user_id");
	        try {
	            resultBean = user1Service.getListTest(user_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value = "/testdetail" , method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getTestDetail(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
	        String test_id = request.getParameter("test_id");
	        try {
	            resultBean = user1Service.getTestDetail(test_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value = "/ability", method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getAbility(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
	        String user_id = request.getParameter("user_id");
	        try {
	            resultBean = user1Service.getAbility(user_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value ="/kpi" ,method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getKPI(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
	        String kpi_id = request.getParameter("kpi_id");
	        try {
	            resultBean = user1Service.getKPI(kpi_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value = "/kpitime",method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getKPIByTime(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
			String user_id = request.getParameter("user_id");
	        String kpi_id = request.getParameter("kpi_id");
	        try {
	            resultBean = user1Service.getKPIByTime(user_id,kpi_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	 
	 @RequestMapping(value = "/skill",method = RequestMethod.GET)
	 @ResponseBody
	 public ResponseEntity<ResultBean> getSkill(HttpServletRequest request)
	 {
			resultBean = new ResultBean();
			String user_id = request.getParameter("user_id");
	        try {
	            resultBean = user1Service.getSkill(user_id);
	        } catch (MessageListException e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
	            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
	            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	        return new ResponseEntity<>(resultBean, user1Service.getStatus());
	 }
	

}
