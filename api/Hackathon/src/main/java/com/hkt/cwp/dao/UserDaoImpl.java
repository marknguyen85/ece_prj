/**
 * 
 */
package com.hkt.cwp.dao;

import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Employee;

/**
 * @author HP
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao implements UserDao{

	@Override
	public Employee getUser(String name, String password) throws Exception {
		Employee user = new Employee();
		user.setId(1);
		user.setName("1");
		user.setPassword("1");
		return user;
	}

	
}
