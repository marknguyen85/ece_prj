/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hkt.cwp.Utils;

public class Constants {

    public static final String API_PATH = "/api";
    public static final String API_VERSION = "v1";
    public static final String NAME_VARIABLE = "name";
    public static final String LANGUAGE = "language";
    public static final int RESULT_SUCCESS = 0;
    public static String MSG_SUCCESS = "Successfully!";
    public static final int RESULT_FAIL = 1;
    public static final String BUSINESS_CONFIG = "configuration";
    public static final String YYYYMMDD_WITH_HYPHEN_FORMAT = "yyyy-MM-dd";
    public static String ERR_ID_INTERNAL_SERVER_ERROR = "Server error!";
    
    public static final String AUTH_EMP = "E";
    public static final String AUTH_MANAGE = "M";
    public static final String ALL = "A";
    
    /* JWT Authen Constant */
    // public static final long EXPIRATIONTIME = 169200000; // 2 days
    public static final long EXPIRATIONTIME = 259200000; // 1 day 86400000
    public static final String SECRET = "secret";
    public static final String HEADER_STRING = "Token";
    
    
    public static final String USER_PATH = "/api/user";
    public static String ERR_ID_FORMAT_JSON = "err_format_json"; 
    public static String ERR_ID_NULL_OR_BLANK = "err_null_or_bank";
    public static String ERR_ID_FORMAT = "err_format";
    public static String ERR_ID_NUMBER_VALID = "err_number_invalid"; 
    public static String ERR_ID_MAX_LENGTH = "err_max_length"; 
    
    public static final String ID_AUTHORIZE_RESULT_FAILED = "api.user.authorzie.fail";
    
    //User
    public static final String USER_ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String PASSWORD = "password";
    public static final String CODE = "code";
    public static final String USER = "user";
    public static final String ERR_ID_REQUEST_TIMEOUT = "505";
    
}
