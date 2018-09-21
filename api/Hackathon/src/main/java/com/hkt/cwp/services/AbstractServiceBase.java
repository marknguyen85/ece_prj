/**
 * 
 */
package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.List;

import com.hkt.cwp.bean.ErrorBean;
import com.hkt.cwp.bean.ResultBean;

/**
 * @author HP
 *
 */
public class AbstractServiceBase {

	protected List<ErrorBean> lstError = new ArrayList<>();
	protected ResultBean resultBean = new ResultBean();
	/**
	 * @return the lstError
	 */
	public List<ErrorBean> getLstError() {
		return lstError;
	}
	
}
