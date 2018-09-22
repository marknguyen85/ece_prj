/**
 * 
 */
package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Employee;

/**
 * @author HP
 *
 */
@Repository
public class UserDaoImpl extends AbstractBaseDao implements UserDao {

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
		return result.size() > 0 ? result.get(0) : null;
	}

	@Override
	public Employee getById(Integer user_id) throws Exception {
		Employee employee = new Employee();

		Query query = (Query) entityManager.createQuery("FROM Employee WHERE id= :user_id");
		query.setParameter("user_id", user_id);
		try {
			employee = (Employee) query.getSingleResult();
		} catch (NoResultException ex) {
			employee = null;
		}

		return employee;
	}

}
