package com.hkt.cwp.dao;

import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.EmployeeTestDetail;

@Repository
public class EmployeeTestDetailDaoImpl extends AbstractBaseDao implements EmployeeTestDetailDao {

	@Override
	public int insertEmployeeTestDetail(EmployeeTestDetail employeeTestDetail) throws Exception {
		entityManager.persist(employeeTestDetail);
		return employeeTestDetail.getId();
	}

}
