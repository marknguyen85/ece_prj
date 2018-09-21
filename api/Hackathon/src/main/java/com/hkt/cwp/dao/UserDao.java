/**
 * 
 */
package com.hkt.cwp.dao;

import java.util.List;

import com.hkt.cwp.models.BaiThi;
import com.hkt.cwp.models.ChiTietBaiThi;
import com.hkt.cwp.models.KPI;
import com.hkt.cwp.models.NangLuc;
import com.hkt.cwp.models.Skill;
import com.hkt.cwp.models.User;

/**
 * @author HP
 *
 */
public interface UserDao {

	User getUser(String name, String password) throws Exception;
	User getUserInfo(Integer user_id) throws Exception;
	List<BaiThi> getListTest(Integer user_id) throws Exception;
	ChiTietBaiThi getTestDetail (Integer test_id) throws Exception;
	NangLuc getAbility (Integer user_id) throws Exception;
	KPI getKPI (Integer kpi_id) throws Exception;
	KPI getKPIByTime(Integer user_id,Integer kpi_id) throws Exception;
	Skill getSkill(Integer user_id) throws Exception;
	
	
	
}
