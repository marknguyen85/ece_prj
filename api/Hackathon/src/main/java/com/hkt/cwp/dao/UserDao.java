/**
 * 
 */
package com.hkt.cwp.dao;

import com.hkt.cwp.models.Employee;

/**
 * @author HP
 *
 */
public interface UserDao {

	Employee getUser(String name, String password) throws Exception;
	Employee getById(Integer user_id) throws Exception;
}
