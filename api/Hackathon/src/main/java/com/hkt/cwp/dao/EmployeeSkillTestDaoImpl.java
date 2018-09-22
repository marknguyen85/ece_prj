package com.hkt.cwp.dao;
/**
 * @author thuan
 */
import java.util.ArrayList;
import java.util.List;

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
	public EmployeeSkillTest getEmployeeSkillTestBySkill(Integer skill_id) throws Exception {
//		Query query = (Query) entityManager.createQuery("FROM EmployeeSkillTest WHERE id= :skillTest_id");
//		query.setParameter("skil6lTest_id", skillTest_id);
//		EmployeeSkillTest emp6loyeeSkillTest = new EmployeeSkillTest();
//		return employeeSkillTest;
		return null;
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

}
