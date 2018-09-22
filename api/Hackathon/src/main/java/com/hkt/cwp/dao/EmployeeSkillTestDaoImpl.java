package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
@Repository
public class EmployeeSkillTestDaoImpl extends AbstractBaseDao implements EmployeeSkillTestDao{

	@Override
	public List<EmployeeSkillTest> getListTest(Integer user_id) throws Exception {
		
		 Query query = (Query) entityManager.createQuery("FROM Employee WHERE id= :user_id");
		 query.setParameter("user_id", user_id);
		 
		 List<EmployeeSkillTest> listEmployeeSkillTest = new ArrayList<>();
		 listEmployeeSkillTest = query.getResultList();
		return listEmployeeSkillTest;
	}

}
