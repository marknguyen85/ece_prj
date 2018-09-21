/**
 * 
 */
package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Employee;

/**
 * @author HP
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao implements UserDao{

	@Override
	public Employee getUser(String userName, String password) throws Exception {
		List<Employee> result = new ArrayList<>();
		
		Session session = null;
		try {
			session = entityManager.unwrap(Session.class);
			Criteria criteria = session.createCriteria(Employee.class);
			criteria.add(Restrictions.eq("username", userName));
			criteria.add(Restrictions.eq("password", password));
			result = criteria.list();
		} catch (Exception e) {
			return null;
		} finally {
            if (entityManager != null && session != null) {
            	session.clear();
                entityManager.close();
            }
        }
		return result.size() > 0 ? result.get(0): null;
	}

	
}
