/**
 * 
 */
package com.hkt.cwp.dao;

import com.hkt.cwp.models.Employee;
import java.util.List;

/**
 * @author HP
 *
 */
public interface UserDao {

	Employee getUser(String name, String password) throws Exception;
	Employee getById(Integer user_id) throws Exception;
    List<Employee> searchEmp(String key, Integer offset) throws Exception;
    
}
