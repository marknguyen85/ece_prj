/**
 * 
 */
package com.hkt.cwp.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author CaoTT
 *
 */
public class AbstractBaseDao {

	protected static EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
