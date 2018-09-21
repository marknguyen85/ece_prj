/**
 * 
 */
package com.hkt.cwp.dao;

import com.hkt.cwp.models.User;

/**
 * @author HP
 *
 */
public interface UserDao {

	User getUser(String name, String password) throws Exception;
}
