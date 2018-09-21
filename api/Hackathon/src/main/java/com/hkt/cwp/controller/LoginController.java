/**
 * 
 */
package com.hkt.cwp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.services.LoginService;

/**
 * @author HP
 *
 */
@RestController
@RequestMapping(Constants.USER_PATH)
public class LoginController {

	private ResultBean resultBean;
	
	@Autowired
	private LoginService service;
	
	 /**
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> login(HttpServletRequest request) {
    	resultBean = new ResultBean();
        String userName = request.getParameter("user_name");
        String password = request.getParameter("password");
        try {
            resultBean = service.login(userName, password);
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
