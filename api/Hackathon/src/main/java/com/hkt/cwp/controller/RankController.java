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
import com.hkt.cwp.services.RankService;

/**
 * @author CaoTT
 *
 */
@RestController
@RequestMapping(Constants.RANK_PATH)
public class RankController {

	private ResultBean resultBean;
	
	@Autowired
	private RankService service;
	
	/**
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ResultBean> ranking(HttpServletRequest request) {
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
     * @param request
     * @return
     */
    @RequestMapping(value = Constants.SEARCH, method = RequestMethod.GET)
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
}
