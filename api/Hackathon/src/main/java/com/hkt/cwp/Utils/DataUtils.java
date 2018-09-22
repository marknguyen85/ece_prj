/*
 * Copyright (C) 2018 Co-Well Asia. All rights reserved.
 * CO-WELL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.hkt.cwp.Utils;

import java.lang.reflect.Field;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.persistence.TemporalType;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class DataUtils {

    private final static Logger logger = LoggerFactory.getLogger(DataUtils.class);
    protected static final SimpleDateFormat YYYYMMDD_HHMMSS_FORMAT = new SimpleDateFormat("yyyyMMdd HHmmss");
    protected static final SimpleDateFormat YYYYMMDD_HHMMSS__WITH_HYPHEN_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    protected static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss");
    protected static final SimpleDateFormat HHMM_FORMAT = new SimpleDateFormat("HH:mm");
    protected static final String HH_MM_PATTERN = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";


    /* Check string null or empty */
    public static boolean isStringNullOrEmpty(Object obj1) {
        return obj1 == null || obj1.toString().trim().isEmpty();
    }

    /**
     * check valid date
     *
     * @param date
     * @return
     *
     */
    public static boolean isValidDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.YYYYMMDD_WITH_HYPHEN_FORMAT);
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param date
     * @param format
     * @return
     * @throws java.text.ParseException
     */
    public static boolean isValidDateWithFormat(String date, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            Date dates = dateFormat.parse(date);
            if (!date.equals(dateFormat.format(dates))) {
                return false;
            }
            dateFormat.setLenient(false);

            dateFormat.parse(date.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * check valid datetime
     *
     * @param datetime
     * @return
     *
     */
    public static boolean isValidDateTime(String datetime) {
        return datetime.matches("\\d{4}-\\d{2}-\\d{2} +\\d{2}:\\d{2}:\\d{2}");
    }

    /**
     * convert string to sqltimestamp
     *
     * @param pattern
     * @param fromDate
     * @return
     *
     */
    public static Timestamp fromStringtoSQLTimestamp(String pattern, String fromDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.setLenient(false);
            Date parsedDate = dateFormat.parse(fromDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return null;
        }
    }

    /**
     * convert string to sqltimestamp GMT0
     *
     * @param pattern
     * @param fromDate
     * @return
     *
     */
    public static Timestamp fromStringtoSQLTimestamp0(String pattern, String fromDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            TimeZone timezone = TimeZone.getTimeZone("GMT0");
            dateFormat.setTimeZone(timezone);
            dateFormat.setLenient(false);
            Date parsedDate = dateFormat.parse(fromDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * convert string to sqltimestamp
     *
     * @param pattern
     * @param fromDate
     * @return
     *
     */
    public static Timestamp fromStringtoSQLTimestamp9(String pattern, String fromDate) {
        if (fromDate == null || fromDate.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            TimeZone timezone = TimeZone.getTimeZone("GMT+9");
            dateFormat.setTimeZone(timezone);
            dateFormat.setLenient(false);
            Date parsedDate = dateFormat.parse(fromDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    public static String fromTimestampToString(String pattern, Timestamp timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        return dateFormat.format(timestamp);
    }

    public static String fromTimestampToString9(String pattern, Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        TimeZone timezone = TimeZone.getTimeZone("GMT+9");
        dateFormat.setTimeZone(timezone);
        dateFormat.setLenient(false);
        return dateFormat.format(timestamp);
    }

    /**
     * convert date GMT+9 to String
     *
     * @param pattern
     * @param date
     * @return
     *
     */
    public static String convertDate9ToString(String pattern, Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        TimeZone timezone = TimeZone.getTimeZone("GMT+9");
        dateFormat.setTimeZone(timezone);
        dateFormat.setLenient(false);
        return dateFormat.format(date);
    }

    /**
     * convert string to date
     *
     * @param dateString
     * @return
     *
     */
    public static Date convertStringToDate(String dateString) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(Constants.YYYYMMDD_WITH_HYPHEN_FORMAT);
        try {
            if (isValidDate(dateString)) {
                date = dateFormat.parse(dateString);
            }
        } catch (ParseException ex) {
            return null;
        }
        return date;
    }

    /**
     *
     * @param strDate
     * @param str
     * @return
     */
    public static Timestamp stringToTimestamp(String strDate, String str) {
        if (strDate == null || strDate.isEmpty()) {
            return null;
        }
        if (strDate.contains(str)) {
            strDate = strDate.replaceAll(str, "");
        }

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.clone();
            calendar.setTime(YYYYMMDD_HHMMSS_FORMAT.parse(strDate));

            return new Timestamp(calendar.getTimeInMillis());
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * check valid time
     *
     * @param time
     * @return
     */
    public static boolean isValidTime(String time) {
        if (time == null || time.isEmpty()) {
            return false;
        }
        try {
            HHMM_FORMAT.setLenient(false);
            Date date = HHMM_FORMAT.parse(time);
            if (date == null) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * convert string to sql Time
     *
     * @param str
     * @return
     */
    public static Time convertStringtoTime(String str) {
        try {
            HHMM_FORMAT.setLenient(false);
            Date date = HHMM_FORMAT.parse(str);
            Time time = new Time(date.getTime());
            return time;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    /**
     * check valid timestamp
     *
     * @param timestamp
     * @return
     */
    public static boolean isValidTimestamp(String timestamp) {
        try {
            YYYYMMDD_HHMMSS__WITH_HYPHEN_FORMAT.setLenient(false);
            Date date = YYYYMMDD_HHMMSS__WITH_HYPHEN_FORMAT.parse(timestamp);
            if (date == null) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * convert String to SQL timestamp
     *
     * @param fromDate
     * @return
     */
    public static Timestamp convertStringtoTimestamp(String fromDate) {
        try {
            YYYYMMDD_HHMMSS__WITH_HYPHEN_FORMAT.setLenient(false);
            Date parsedDate = YYYYMMDD_HHMMSS__WITH_HYPHEN_FORMAT.parse(fromDate);
            Timestamp timestamp = new Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * get Current Date
     *
     * @author CaoTT
     * @return
     */
    public static Timestamp getCurrentDate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp;
    }

    /**
     * validate length input
     *
     * @author NgocND
     * @param value
     * @param length of each field
     * @return
     */
    public static String showLengthValid(String value, int length) {
        return BundleUtils.getString(value).replace("@length", String.valueOf(length));
    }

    /**
     * validate format input
     *
     * @param value
     * @param format
     * @return
     */
    public static String showFormatValid(String value, String format) {
        return BundleUtils.getString(value).replace("@format", String.valueOf(format));
    }

    /**
     * replace param in error message
     *
     * @param value
     * @param param
     * @return
     */
    public static String getMessageHasParam(String value, String[] param) {
        String msg = BundleUtils.getString(value);
        if (param != null && param.length > 0) {
            for (int i = 0; i < param.length; i++) {
                msg = msg.replace("{" + i + "}", param[i]);
            }
        }
        return msg;
    }

    /**
     * Get Json Object
     *
     * @param strJson
     * @return
     * @throws java.lang.Exception
     */
    public static JsonObject getJsonObject(String strJson) throws Exception {
        JsonObject json = null;
        try {
            json = JsonDataUtil.createJsonObject(strJson);
            json = json.getAsJsonObject("data");
        } catch (JsonSyntaxException ex) {
            json = null;
        }
        return json;
    }


    /**
     * convert from object to other
     *
     * @author CaoTT
     * @param <E>
     * @param obj
     * @param o
     * @return
     * @throws Exception
     */
    public static <E> E createData(Object obj, Class<E> o) throws Exception {
        E result = null;
        Class<? extends Object> objClass = obj.getClass();
        Field[] fieldsObj = objClass.getDeclaredFields();
        Field[] fieldsO = o.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();

        for (Field field : fieldsO) {
            field.setAccessible(true);
            fieldMap.put(field.getName(), field);
        }
        try {
            result = o.newInstance();
            for (Field fieldsObj1 : fieldsObj) {
                String name = fieldsObj1.getName();
                fieldsObj1.setAccessible(true);
                Object val = fieldsObj1.get(obj);
                if (!fieldMap.containsKey(name)) {
                    continue;
                }
                Field field = fieldMap.get(name);
                if (val == null) {
                    if (field.getType().isPrimitive()) {
                        //プリミティブ変数に NULL は設定できないので次へ
                        continue;
                    }
                } else if (val instanceof Timestamp && field.getType() == String.class) {
                    val = JsonDataUtil.timestampToString((Timestamp) val);
                } else if (val instanceof Date && field.getType() == String.class) {
                    val = JsonDataUtil.dateToString((Date) val);
                } else if (field.getType() != val.getClass()) {
                    Class<?> ft = field.getType();

                    if (!ft.isPrimitive() && !(val instanceof Integer)) {
                        //型が合わないので読み飛ばし
                        continue;
                    }

                    if (ft == String.class && val instanceof Integer) {
                        val = val.toString();
                    } else if (ft == int.class && val instanceof Integer) {
                        //問題なし
                    } else {
                        continue;
                    }
                }
                field.set(result, val);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | SecurityException e) {
            throw e;
        }
        return result;
    }

    /**
     *
     * @author: ThuanNV
     * @param date
     * @return
     *
     */
    public static Date getSqlDate(java.util.Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return new Date(cal.getTimeInMillis());
    }
    
    /**
	 *
	 * @param value
	 * @return
	 */
	public static boolean isInteger(String value) {
		final Pattern ptnInteger = Pattern.compile("^\\d+$");
		try {
			return ptnInteger.matcher(value).matches();
		} catch (PatternSyntaxException e) {
			return false;
		}
	}
	
	  /**
     * Memo set parameter to query
     *
     * @author CaoTT
     * @param paramList List<Object>
     * @param query Query
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void setParameterCommon(List<Object> paramList, Query query) {
        int idx = 0;
        for (Object obj : paramList) {
            if (obj instanceof Timestamp) {
                query.setParameter(idx++, obj, TemporalType.TIMESTAMP);
            } else {
                query.setParameter(idx++, obj);
            }

        }
    }
}
