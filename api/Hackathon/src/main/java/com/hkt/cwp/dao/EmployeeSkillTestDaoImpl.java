package com.hkt.cwp.dao;

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

}
