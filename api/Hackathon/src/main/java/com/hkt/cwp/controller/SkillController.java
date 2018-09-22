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
import com.hkt.cwp.services.SkillService;

/**
 * 
 * @author thuan
 *
 */
@RestController
@RequestMapping(Constants.SKILL_PATH)
public class SkillController {
	
	private ResultBean resultBean;

	@Autowired
	private SkillService skillService;
	@RequestMapping(value ="",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBean> getListKPI(HttpServletRequest request) {
		resultBean = new ResultBean();
		String kpi_id = request.getParameter("kpi_id");
		try {
			resultBean = skillService.getListKPI(kpi_id);
		} catch (MessageListException e) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
			return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.RESULT_FAIL,
					BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
			return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(resultBean, skillService.getStatus());
	}
	@RequestMapping(value="/kpibytime",method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ResultBean> getListKPIByTime(HttpServletRequest request) {
		resultBean = new ResultBean();
		String kpi_id = request.getParameter("kpi_id");
		String user_id = request.getParameter("user_id");
		try {
			resultBean = skillService.getListKPIByTime(user_id,kpi_id);
		} catch (MessageListException e) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, "", null, e.getLstError());
			return new ResponseEntity<>(resultBean, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			resultBean = new ResultBean(Constants.RESULT_FAIL,
					BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
			return new ResponseEntity<>(resultBean, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(resultBean, skillService.getStatus());
	}

}
