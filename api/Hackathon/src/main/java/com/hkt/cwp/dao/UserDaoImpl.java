/**
 * 
 */
package com.hkt.cwp.dao;

import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.User;

/**
 * @author HP
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao implements UserDao{

	@Override
	public User getUser(String name, String password) throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("1");
		user.setPassword("1");
		return user;
	}

	
}
