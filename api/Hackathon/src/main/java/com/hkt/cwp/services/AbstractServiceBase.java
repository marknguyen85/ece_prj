/**
 * 
 */
package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.hkt.cwp.bean.ErrorBean;
import com.hkt.cwp.bean.ResultBean;

/**
 * @author HP
 *
 */
public class AbstractServiceBase {

	protected List<ErrorBean> lstError = new ArrayList<>();
	protected ResultBean resultBean = new ResultBean();
	protected HttpStatus status;
	/**
	 * @return the lstError
	 */
	public List<ErrorBean> getLstError() {
		return lstError;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
}
