/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkt.cwp.controller;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.services.TechniqueService;
import com.hkt.cwp.services.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author HP
 */
@RestController
@RequestMapping(Constants.TECHNIQUE_PATH)
public class TechniqueController {
    private ResultBean resultBean;
	
	@Autowired
	private TechniqueService service;
	
	/**
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> getTechniqueById(HttpServletRequest request) {
    	resultBean = new ResultBean();
        String empId = request.getParameter("userId");
        try {
            resultBean = service.getTechniqueById(empId);
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
