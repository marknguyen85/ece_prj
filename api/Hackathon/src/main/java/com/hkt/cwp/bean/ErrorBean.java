/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hkt.cwp.bean;

import java.io.Serializable;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.DataUtils;

/**
 * @author CaoTT
 * @create 20180131
 *
 */
public class ErrorBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private String field;
	private String message;
	
	public ErrorBean() {
	}

	public ErrorBean(String code, String field, String message) {
		this.code = code;
		this.field = field;
		this.message = message;
	}

	public ErrorBean(String code, String field) {
        this.code = code;
        this.field = field;
        this.message = BundleUtils.getString(code);
    }
	
	public ErrorBean(String code, String field,String[] param) {
        this.code = code;
        this.field = field;
        this.message = DataUtils.getMessageHasParam(code,param);
    }
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ErrorBean buildFromString(String field, String msgDefault) {
		return new ErrorBean(msgDefault, field, BundleUtils.getString(msgDefault));
	}
	
}




