/**
 * 
 */
package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;

import com.hkt.cwp.Utils.DataUtils;

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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> List<T> getListData(String sql, List<Object> paramList, Integer offset, Integer limit, Class<T> o)
			throws Exception {
		List<T> lst = new ArrayList<>();
		try {
			Query query = (Query) entityManager.createQuery(sql);
			if (!paramList.isEmpty()) {
				DataUtils.setParameterCommon(paramList, query);
			}
			if (offset != null) {
				query.setFirstResult(offset);
			}

			if (limit != null) {
				query.setMaxResults(limit);
			}
			lst = (List<T>) query.getResultList();
		} catch (Exception e) {
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return lst;
	}

	/**
	 *
	 * @param sql
	 * @param paramList
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Object[]> excuteNativeQuery(String sql, List<Object> paramList, Integer offset, Integer limit)
			throws Exception {
		List<Object[]> lst = new ArrayList<>();
		try {
			Query query = (Query) entityManager.createNativeQuery(sql);
			for (int i = 0; i < paramList.size(); i++) {
				query.setParameter(i + 1, paramList.get(i));
			}

			if (limit != null && offset != null) {
				query.setFirstResult(offset);
				query.setMaxResults(limit);
			}
			lst = query.getResultList();
		} catch (Exception e) {
			throw e;
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}

		return lst;
	}
}
