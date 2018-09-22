/**
 * 
 */
package com.hkt.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.Utils.StringUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import java.util.List;

/**
 * @author HP
 *
 */
@Service
public class UserServiceImpl extends AbstractServiceBase implements UserService{

	@Autowired
	private transient UserDao userDao;

	@Override
	public ResultBean getUser(String userName, String password) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Employee user = userDao.getUser(userName, password);
		if (user == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(user);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean registerUser(String jsonData) throws MessageListException, Exception {
		resultBean = new ResultBean();
        // Get JSON Object
        JsonObject json = JsonDataUtil.getJsonObject(jsonData);

        // The JSON format is incorrect.
        if (null == json) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
            status = HttpStatus.BAD_REQUEST;
            return resultBean;
        }
        
        Employee user = new Employee();
        user = (Employee) createObjecFromJson(json, user, null, null, null);
        
        resultBean.setResult(Constants.RESULT_SUCCESS);
        resultBean.setMessage(Constants.MSG_SUCCESS);
        resultBean.setData(user);
		status = HttpStatus.OK;
        return resultBean;
	}
	
	
	public ResultBean authority(String jsonData) throws MessageListException , Exception {
		resultBean = new ResultBean();
		// Get JSON Object
        JsonObject json = JsonDataUtil.getJsonObject(jsonData);

        // The JSON format is incorrect.
        if (null == json) {
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
            status = HttpStatus.BAD_REQUEST;
            return resultBean;
        }
        String userName = JsonDataUtil.getJsonString(json, "username");
        String password = JsonDataUtil.getJsonString(json, "password");
        Employee user = userDao.getUser(userName, password);
        if (null == user) {
        	// Not found user
            status = HttpStatus.UNAUTHORIZED;
            resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
        } else {
        	resultBean.setResult(Constants.RESULT_SUCCESS);
        	resultBean.setMessage(Constants.MSG_SUCCESS);
        	resultBean.setData(user);
        }
        
		return resultBean;
		
	}
	
        //Search Employee
	@Override
	public ResultBean searchEmp(String key, String page) throws MessageListException, Exception {
		resultBean = new ResultBean();
		int offset = 0;
		if (!StringUtil.isEmpty(page)) {
			offset = Integer.parseInt(page) * Constants.LIMIT -1; 
		}
		List<Employee> lstEmp = userDao.searchEmp(key, offset);
		if (lstEmp == null || lstEmp.size() == 0) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(lstEmp);
		status = HttpStatus.OK;
		return resultBean;
	}
}
