package com.hkt.cwp.dao;
/**
 * @author thuan
 */
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;

@Repository
public class EmployeeSkillTestDaoImpl extends AbstractBaseDao implements EmployeeSkillTestDao {

	@Override
	public EmployeeSkillTest getListTest(Integer skillTest_id) throws Exception {

		Query query = (Query) entityManager.createQuery("FROM EmployeeSkillTest WHERE id= :skillTest_id");
		query.setParameter("skillTest_id", skillTest_id);
		EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
		return employeeSkillTest;
	}

	@Override
	public int insertEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) throws Exception {
		try {
			 entityManager.persist(employeeSkillTest);
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
		
		return employeeSkillTest.getId();
	}

	@Override
	public int updateEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) throws Exception {
		entityManager.merge(employeeSkillTest);
		return employeeSkillTest.getId();
	}
	
	@Override
	public List<EmployeeSkillTest> getAll() throws Exception {
		List<EmployeeSkillTest> listEmployeeSkillTest = new ArrayList<>();
		try {
			Query query = (Query) entityManager.createQuery("FROM EmployeeSkillTest e");
			listEmployeeSkillTest = query.getResultList();
		} catch (Exception e) {
			throw e;
		}
		
		return listEmployeeSkillTest;
	}

	@Override
	public List<EmployeeSkillTest> getESTBySkillId(Integer skillId) throws Exception {
		List<EmployeeSkillTest> listEmployeeSkillTest = new ArrayList<>();
		try {
			Query query = (Query) entityManager.createQuery("FROM EmployeeSkillTest e WHERE e.skill.id = :skillId order by point desc");
			query.setParameter("skillId", skillId);
			listEmployeeSkillTest = query.getResultList();
		} catch (Exception e) {
			throw e;
		}
		
		return listEmployeeSkillTest;
	}

}
