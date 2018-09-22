package com.hkt.cwp.dao;

import com.hkt.cwp.models.Skill;

public interface SkillDao {
	Skill getById(Integer skill_id) throws Exception;
}
