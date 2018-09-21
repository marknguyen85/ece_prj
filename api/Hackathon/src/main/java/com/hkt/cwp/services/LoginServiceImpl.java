/**
 * 
 */
package com.hkt.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.User;

/**
 * @author HP
 *
 */
@Service
public class LoginServiceImpl extends AbstractServiceBase implements LoginService{

	@Autowired
	private transient UserDao userDao;

	@Override
	public ResultBean login(String userName, String password) throws MessageListException, Exception {
		User user = userDao.getUser(userName, password);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage("Successfully!");
		resultBean.setData(user);
		status = HttpStatus.OK;
		return resultBean; 
	}
	
	
}
