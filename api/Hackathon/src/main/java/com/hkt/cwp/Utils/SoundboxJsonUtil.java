/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hkt.cwp.Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author kamimura
 *
 */
public class SoundboxJsonUtil {

	protected static final Logger logger = LoggerFactory.getLogger(SoundboxJsonUtil.class);

	protected static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyyMMdd");
	protected static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HHmmss");
	protected static final SimpleDateFormat YYYYMMDD_HHMMSS_FORMAT = new SimpleDateFormat("yyyyMMdd HHmmss");

	public static Map<String, Object> createResultMap(int result, String message, Object data) {
		return createResultMap(result, message, null, null, data);
	}

	public static Map<String, Object> createResultMap(int result, String message, Integer count, Integer maxPage, Object data) {
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("result", result);
		map.put("message", message);
		map.put("data", data);
		if(count != null) {
			map.put("count", count);
		}
		if(maxPage != null) {
			map.put("maxpage", maxPage);
		}

		return map;
	}


	public static Integer stringToInteger(String s){
		if(s == null || s.isEmpty()) {
			return null;
		}
		return new Integer(s);
	}

	/**
	 * @param json
	 * @return
	 */
	public static JsonObject createJsonObject(String json){
		return new Gson().fromJson(json, JsonObject.class);
	}
	/**
	 */
	public static boolean has(JsonObject json,String memberName){
		if (!json.has(memberName) || json.get(memberName).isJsonNull()){
			return false;
		}
		return true;
	}
	
	/**
     */
    public static boolean hasMember(JsonObject json,String memberName){
        return json.has(memberName);
    }
    
	/**
	 */
	public static String getJsonString(JsonObject json,String memberName){
		return getJsonString(json,memberName,null);
	}
	/**
	 */
	public static String getJsonString(JsonObject json,String memberName,String defaultValue){
		if (!json.has(memberName) || json.get(memberName).isJsonNull()){
			return defaultValue;
		}
		return json.get(memberName).getAsString();
	}
	/**
	 */
	public static Integer getJsonInteger(JsonObject json,String memberName){
		return getJsonInteger(json,memberName,null);
	}
	/**
	 */
	public static Integer getJsonInteger(JsonObject json,String memberName,Integer defaultValue){
		if (!json.has(memberName) || json.get(memberName).isJsonNull()){
			return defaultValue;
		}
		try {
			return json.get(memberName).getAsInt();
		}
		catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> createJsonMap(String json){
		return (Map<String, Object>)new Gson().fromJson(json, Map.class);
	}
	/**
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getJsonMap(Map<String,Object> json,String memberName){
		return (Map<String,Object>)json.get(memberName);
	}
	/**
	 */
	public static String getJsonString(Map<String,Object> json,String memberName){
		return getJsonString(json,memberName,null);
	}
	/**
	 */
	public static String getJsonString(Map<String,Object> json,String memberName,String defaultValue){
		Object value = json.get(memberName);
		if (value==null){
			return defaultValue;
		}else if (value instanceof String){
			return (String)value;
		}else if (value instanceof Number){
			Number num = (Number)json.get(memberName);
			return String.valueOf(num.intValue());
		}else{
			return value.toString();
		}
	}
	/**
	 */
	public static Integer getJsonInteger(Map<String,Object> json,String memberName){
		return getJsonInteger(json,memberName,null);
	}
	/**
	 */
	public static Integer getJsonInteger(Map<String,Object> json,String memberName,Integer defaultValue){
		Object value = json.get(memberName);
		if (value==null){
			return defaultValue;
		}else if (value instanceof String){
			String str = (String)value;
			if (str.length()==0){
				return defaultValue;
			}else{
				return Integer.valueOf(str);
			}
		}else{
			Number num = (Number)json.get(memberName);
			if (num instanceof Integer){
				return (Integer)num;
			}else{
				return num.intValue();
			}
		}
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static Date stringToDate(String s) {
		if(s == null || s.isEmpty()) {
			return null;
		}
		if(s.indexOf("/") != -1) {
			s = s.replaceAll("/", "");
		}

		try {
			return YYYYMMDD_FORMAT.parse(s);
		}
		catch(Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
	 * @param s
	 * @return
	 */
	public static Timestamp stringToTimestamp(String s) {
		if(s == null || s.isEmpty()) {
			return null;
		}
		if(s.indexOf("/") != -1) {
			s = s.replaceAll("/", "");
		}

		try {
			Calendar calendar = Calendar.getInstance();
			calendar.clone();
			calendar.setTime(YYYYMMDD_HHMMSS_FORMAT.parse(s));

			return new Timestamp(calendar.getTimeInMillis());
		}
		catch(Exception e) {
			logger.error("", e);
			return null;
		}
	}
	
	/**
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if(date == null) {
            return null;
        }

        try {
            return YYYYMMDD_FORMAT.format(date);
        }
        catch(Exception e) {
            logger.error("", e);
            return null;
        }
    }
    
    /**
	 * @param ts
	 * @return
	 */
	public static String timestampToString(Timestamp ts) {
		if(ts == null) {
			return null;
		}

		try {
			return YYYYMMDD_HHMMSS_FORMAT.format(ts);
		}
		catch(Exception e) {
			logger.error("", e);
			return null;
		}
	}
    
    /**
     * Get Json Object
     * 
     * @param strJson
     * @return 
     * @throws java.lang.Exception
     */
    public static JsonObject getJsonObject(String strJson) {
        JsonObject json = null;
        try {
            if (null != strJson && 0 != strJson.length()) {
                json = SoundboxJsonUtil.createJsonObject(strJson);
            }
            if (json != null) {
                json = json.getAsJsonObject("data");
            }
        } catch (Exception ex) {
            json = null;
        }
        return json;
    }
    
    /**
     * convert from String to  JsonObject
     * @author CaoTT
     * @param strJson
     * @return 
     * @throws java.lang.Exception
     */
    public static JsonObject convertStringToJson(String strJson) {
    	 JsonObject json = null;
    	 JsonParser jsonParser = new JsonParser();
         try {
     		json = (JsonObject) jsonParser.parse(strJson);
         } catch (Exception ex) {
             json = null;
         }
         return json;
    }
    
}
