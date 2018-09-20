/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.hkt.cwp.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response infomation
 *
 * @author CaoTT
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // Code result (0: Success, failure except 0)
    private Integer result = null;

    // Message result
    private String message = null;
    
    private Long count = null;
    
    private Long maxpage = null;

    // Data result
    @JsonProperty
    private Object data = null;

    // Errors result
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ErrorBean> errors = new ArrayList<>();

    public ResultBean() {

    }
    
    public ResultBean(Integer result) {
        this.result = result;
    }
    
    public ResultBean(Integer result, String message, Object data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }
    
    public ResultBean(Integer result, Object data) {
        this.result = result;
        this.data = data;
    }

    public ResultBean(Integer result, String message, Object data, List<ErrorBean> errors) {
        this.result = result;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public ResultBean(Integer result, String message) {
        this.result = result;
        this.message = message;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<ErrorBean> getErrors() {
        return errors;
    }

    public void addValidationError(ErrorBean error) {
        errors.add(error);
    }

    public void addAllValidationError(List<ErrorBean> error) {
        errors.addAll(error);
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getMaxpage() {
        return maxpage;
    }

    public void setMaxpage(Long maxpage) {
        this.maxpage = maxpage;
    }

}
