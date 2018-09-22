package com.hkt.cwp.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.TestDetail;

@Repository
public class TestDetailDaoImpl extends AbstractBaseDao implements TestDetailDao {

	@Override
	public TestDetail getById(Integer testDetai_id) throws Exception {
		TestDetail testDetail = new TestDetail();
	    try {
	    	testDetail = entityManager.find(TestDetail.class, testDetai_id);
        } catch (NoResultException ex) {
        	testDetail = null;
        } finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
        return testDetail;
	}

}
