/**
 * 
 */
package com.hkt.cwp.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hkt.cwp.bean.ErrorBean;

/**
 * @author CaoTT
 *
 */
public class ValidationUtil {

	/**
     * Validate datetime by format
     *
     * @param value
     * @param format
     * @param messageList
     * @param fieldName
     * @param isNull
     * @return
     */
    public static Timestamp validateDateTime(String value, String format, List<ErrorBean> messageList, String fieldName,
            boolean isNull) {
        if (value == null || value.isEmpty()) {
            if (isNull) {
                return null;
            } else {
                ErrorBean errorBean = new ErrorBean(Constants.ERR_ID_NULL_OR_BLANK, fieldName);
                messageList.add(errorBean);
                return null;
            }
        }
        Timestamp date = null;
        if (value == null || value.isEmpty()) {
            if (!isNull) {
                ErrorBean errorBean = new ErrorBean(Constants.ERR_ID_NULL_OR_BLANK, fieldName);
                messageList.add(errorBean);
            }
        } else {
            value = value.trim();
            date = DataUtils.fromStringtoSQLTimestamp9(format, value);
            if (date == null) {
                ErrorBean errorBean = new ErrorBean(Constants.ERR_ID_FORMAT, fieldName);
                errorBean.setMessage(DataUtils.showFormatValid(Constants.ERR_ID_FORMAT, format));
                messageList.add(errorBean);
            }
        }
        return date;
    }
    
    /**
     * Validate date by format
     *
     * @param value
     * @param format
     * @param messageList
     * @param fieldName
     * @param isNull
     * @return
     * @throws java.text.ParseException
     */
    public static Date validateDate(String value, String format, List<ErrorBean> messageList, String fieldName, boolean isNull)
            throws ParseException {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setLenient(false);
        if (value == null || value.isEmpty()) {
            if (!isNull) {
                messageList.add(new ErrorBean(Constants.ERR_ID_NULL_OR_BLANK, fieldName));
            }
        } else {
            value = value.trim();
            if (!DataUtils.isValidDateWithFormat(value, format) || value.length() > format.length()) {
                messageList.add(new ErrorBean(Constants.ERR_ID_FORMAT, fieldName,
                        DataUtils.showFormatValid(Constants.ERR_ID_FORMAT, format)));
            } else {
                date = (Date) dateFormat.parse(value.trim());
                if (date == null) {
                    messageList.add(new ErrorBean(Constants.ERR_ID_FORMAT, fieldName,
                            DataUtils.showFormatValid(Constants.ERR_ID_FORMAT, format)));
                }
            }
        }
        return date;
    }
    
    /**
     * Validate Integer
     *
     * @param value
     * @param messageList
     * @param key
     * @param isNull
     * @param isNoMoreCheck
     * @param isRetNull
     * @return
     * @throws Exception
     */
    public static Integer validateInt(String value, List<ErrorBean> messageList, String key, boolean isNull,
            boolean isNoMoreCheck) throws Exception {
        Integer result = isNoMoreCheck ? 0 : null;
        if (null == value) {
            if (isNull) {
                return null;
            } else {
                messageList.add(new ErrorBean(Constants.ERR_ID_NULL_OR_BLANK, key));
            }
        } else {
            if (value.length() == 0) {
                if (isNull) {
                    return null;
                } else {
                    messageList.add(new ErrorBean(Constants.ERR_ID_NULL_OR_BLANK, key));
                }
            } else if (!DataUtils.isInteger(value)) {
                messageList.add(new ErrorBean(Constants.ERR_ID_NUMBER_VALID, key));
            } else if (value.length() > 9) {
                ErrorBean errorBean = new ErrorBean();
                errorBean.setCode(Constants.ERR_ID_MAX_LENGTH);
                errorBean.setMessage(DataUtils.showLengthValid(Constants.ERR_ID_MAX_LENGTH, 9));
                errorBean.setField(key);
                messageList.add(errorBean);
            } else {
                result = Integer.parseInt(value);
            }
        }

        return result;
    }
}
