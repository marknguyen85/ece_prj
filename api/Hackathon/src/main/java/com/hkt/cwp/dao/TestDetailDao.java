package com.hkt.cwp.dao;

import com.hkt.cwp.models.TestDetail;

public interface TestDetailDao {
	TestDetail getById(Integer testDetai_id) throws Exception;
}
