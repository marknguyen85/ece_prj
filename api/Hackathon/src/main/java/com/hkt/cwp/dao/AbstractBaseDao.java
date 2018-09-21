/**
 * 
 */
package com.hkt.cwp.dao;

import javax.persistence.EntityManager;

/**
 * @author CaoTT
 *
 */
public class AbstractBaseDao {

	protected static EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
