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
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.services.TestDetailService;

@RestController
@RequestMapping(Constants.TEST_PATH)
public class TestDetailController {

private ResultBean resultBean;
	
	@Autowired
	private TestDetailService testDetailService;
	
	
	@RequestMapping(value = "/index" ,method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> getUser(HttpServletRequest request) {
    	resultBean = new ResultBean();
        try {
            resultBean = testDetailService.insertTest(request);
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, testDetailService.getStatus());
    }
    
	@RequestMapping(value = "/update" ,method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<ResultBean> updateTest(@RequestBody String json) {
    	resultBean = new ResultBean();
        try {
            resultBean = testDetailService.updateTest(json);
        } catch (MessageListException e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
            return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
            return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(resultBean, testDetailService.getStatus());
    }
    
}
