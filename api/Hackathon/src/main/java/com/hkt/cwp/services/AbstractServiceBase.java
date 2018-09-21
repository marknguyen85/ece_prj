/**
 * 
 */
package com.hkt.cwp.services;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.DataUtils;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.Utils.StringUtil;
import com.hkt.cwp.Utils.ValidationUtil;
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
	
	/**
     * create object from json
     *
     * @author CaoTT
     * @param <E>
     * @param obj
     * @param o
     * @param formatTime
     * @param formatDate
     * @return
     * @throws Exception
     */
    public Object createObjecFromJson(JsonObject obj, Object result, String formatTime, String formatDate, String arrayIndex) throws Exception {
//        E result = null;
        Class<? extends Object> objClass = result.getClass();
        Field[] fieldsO = objClass.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fieldsO) {
            field.setAccessible(true);
            String name = field.getName();
            fieldMap.put(name, field);
        }
        try {
//            result = o.newInstance();
            for (Field fieldsObj1 : fieldsO) {
                String name = fieldsObj1.getName();
                fieldsObj1.setAccessible(true);
                if (!obj.has(name)) {
                    continue;
                }
                String val = JsonDataUtil.getJsonString(obj, name);
                Object value = null;
                Field field = fieldMap.get(name);
                if (!StringUtil.isEmpty(arrayIndex)) {
                	name += " " + arrayIndex;
                }
                if (StringUtil.isEmpty(val)) {
            		value = null;
            	} else if (field.getType()== Timestamp.class) {
                	value = ValidationUtil.validateDateTime(val, formatTime, lstError, name, true);
                	if (value == null) {
                		value = DataUtils.getCurrentDate();
                	}
                } else if (field.getType()== Date.class) {
                	value = ValidationUtil.validateDate(val, formatDate, lstError, name, true);
                	if (value == null) {
                		value = new Date();
                	}
                }  else if (field.getType()== Integer.class) {
                	value = ValidationUtil.validateInt(val, lstError, name, true, false);
                	if (null == value) {
                		value = -1;
                	}
                } else if (field.getType()== String.class) {
                	value = val;
                } else {
                	continue;
                }
                if (value == null) {
                    if (field.getType().isPrimitive()) {
                        //プリミティブ変数に NULL は設定できないので次へ
                        continue;
                    }
                } 
                field.set(result, value);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException e) {
            throw e;
        }
        return result;
    }
	
}
