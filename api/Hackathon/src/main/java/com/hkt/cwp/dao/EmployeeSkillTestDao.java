package com.hkt.cwp.dao;
/**
 * @author thuan
 */
import java.util.List;

import com.hkt.cwp.models.EmployeeSkillTest;

public interface EmployeeSkillTestDao {
	EmployeeSkillTest getListTest(Integer skillTest_id) throws Exception;
	int insertEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) throws Exception;
	int updateEmployeeSkillTest(EmployeeSkillTest employeeSkillTest) throws Exception;
	EmployeeSkillTest getEmployeeSkillTestBySkill(Integer skill_id) throws Exception;
	
}
