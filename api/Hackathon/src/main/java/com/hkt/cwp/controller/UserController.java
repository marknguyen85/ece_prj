/**
 * 
 */
package com.hkt.cwp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.services.UserService;

/**
 * @author HP
 *
 */
@RestController
@RequestMapping(Constants.USER_PATH)
public class UserController {

	private ResultBean resultBean;
	
	@Autowired
	private UserService service;
	
	/**
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> getUser(HttpServletRequest request) {
    	resultBean = new ResultBean();
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        try {
            resultBean = service.getUser(userName, password);
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, service.getStatus());
    }
    
    @RequestMapping(value = "/authorize", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResultBean> authorize(@RequestBody String jsonData) {
    	resultBean = new ResultBean();
        try {
            resultBean = service.authority(jsonData);
//            if (resultBean.getResult() == Constants.RESULT_SUCCESS) {
//            	String token = TokenAuthentication.addAuthentication((Employee) resultBean.getData());
//            	HttpHeaders headers = new HttpHeaders();
//                headers.set("token", token);
//                return new ResponseEntity<>(resultBean, headers, HttpStatus.CREATED);
//            }
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, service.getStatus());
    }
	
    //Get list Employee
    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> searchEmp(HttpServletRequest request) {
    	resultBean = new ResultBean();
        String key = request.getParameter("key");
        String page = request.getParameter("page");
        String skillId = request.getParameter("skill_id");
        try {
            resultBean = service.searchEmp(key, page, skillId);
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, service.getStatus());
    }
    /**
     * @author thuan 
     * @param request
     * @return
     */
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> getHistory(HttpServletRequest request) {
    	resultBean = new ResultBean();
        String user_id = request.getParameter("user_id");
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        try {
            resultBean = service.getHistory(user_id, from, to);
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, service.getStatus());
    }
}
