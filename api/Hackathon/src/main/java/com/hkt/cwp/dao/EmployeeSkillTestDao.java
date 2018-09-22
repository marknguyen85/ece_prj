package com.hkt.cwp.dao;

import java.util.List;

import com.hkt.cwp.models.EmployeeSkillTest;

public interface EmployeeSkillTestDao {
	List<EmployeeSkillTest> getListTest(Integer user_id) throws Exception;
	
}
