package com.hkt.cwp.dao;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Skill;

@Repository
public class SkillDaoImpl extends AbstractBaseDao implements SkillDao {

	@Override
	public Skill getById(Integer skill_id) throws Exception {
		@SuppressWarnings("rawtypes")
		Query query = (Query) entityManager.createQuery("FROM Skill WHERE id= :skillTest_id");
		query.setParameter("skillTest_id", skill_id);
		Skill skill = new Skill();
		skill = (Skill) query.getSingleResult();
		return skill;
	}

}
