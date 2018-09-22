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
	
	List<EmployeeSkillTest> getAll() throws Exception;
	List<EmployeeSkillTest> getESTBySkillId(Integer skillId) throws Exception;
	
	List<Object[]> excuteNativeQuery(String sql, List<Object> paramList, Integer offset, Integer limit) throws Exception;
}
