/**
 * 
 */
package com.hkt.cwp.dao;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.hkt.cwp.models.Technique;

/**
 * @author CaoTT
 *
 */
@Repository
public class TechniqueDaoImpl  extends AbstractBaseDao implements TechniqueDao{

	@Override
	public Technique getTechniqueById(Integer techId) throws Exception {
		Technique tech = new Technique();
        try {
        	tech = entityManager.find(Technique.class, techId);
        } catch (NoResultException ex) {
        	tech = null;
        }
        return tech;
	}

}
