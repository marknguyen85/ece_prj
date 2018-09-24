/**
 * 
 */
package com.hkt.cwp.dao;

import com.hkt.cwp.models.Technique;

/**
 * @author HP
 *
 */
public interface TechniqueDao {

	Technique getTechniqueById(Integer techId) throws Exception;
}
