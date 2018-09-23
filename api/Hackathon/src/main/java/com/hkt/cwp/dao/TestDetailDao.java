package com.hkt.cwp.dao;

import java.util.List;

import com.hkt.cwp.models.TestDetail;

public interface TestDetailDao {
	TestDetail getById(Integer testDetai_id) throws Exception;
	List<TestDetail> getListTestDetailBySkill(Integer skill_id) throws Exception;
}
