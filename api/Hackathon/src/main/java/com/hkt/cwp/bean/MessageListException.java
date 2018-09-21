package com.hkt.cwp.bean;

import java.util.ArrayList;
import java.util.List;

public class MessageListException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ErrorBean> lstError;
	
	public MessageListException() {
		this.lstError = new ArrayList<>();
	}
	
	public MessageListException(List<ErrorBean> lstError) {
		this.lstError = lstError;
	}

	public MessageListException(ErrorBean error) {
        this.lstError = new ArrayList<>();
        this.lstError.add(error);
    }

	/**
	 * @return the lstError
	 */
	public List<ErrorBean> getLstError() {
		return lstError;
	}
	
	
}
