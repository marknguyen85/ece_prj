/**
 * 
 */
package com.hkt.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.UserDao;

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
		
		return resultBean;
	}
	
	
}
