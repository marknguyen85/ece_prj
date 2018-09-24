package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.query.Query;
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

	@Override
	public List<TestDetail> getListTestDetailBySkill(Integer skill_id) throws Exception {
		List<TestDetail> listTestDetail = new ArrayList();
		try {
			Query query = (Query) entityManager.createQuery(
					"FROM TestDetail e WHERE e.skill.id = :skillId ");
			query.setParameter("skillId", skill_id);
			listTestDetail= query.getResultList();
        } catch (NoResultException ex) {
        	listTestDetail = null;
        } finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
        return listTestDetail;
	}

}
