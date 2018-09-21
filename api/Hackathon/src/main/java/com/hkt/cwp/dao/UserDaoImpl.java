/**
 * 
 */
package com.hkt.cwp.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

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
@Repository
public class UserDaoImpl extends AbstractBaseDao implements UserDao{

	@Override
	public User getUser(String name, String password) throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("1");
		user.setPassword("1");
		return user;
	}

	@Override
	public User getUserInfo(Integer user_id) throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("1");
		user.setPassword("1");
		return user;
	}

	@Override
	public List<BaiThi> getListTest(Integer user_id) throws Exception {
		List<BaiThi> list = new ArrayList<>();
		
		BaiThi baithi = new BaiThi();
		baithi.setBaiThi_id(1);
		baithi.setBaiThi_name("Java coding");
		
		
		list.add(baithi);
		return list;
	}

	@Override
	public ChiTietBaiThi getTestDetail(Integer test_id) throws Exception {
		ChiTietBaiThi chiTietBaiThi = new ChiTietBaiThi();
		
		return chiTietBaiThi;
	}

	@Override
	public NangLuc getAbility(Integer user_id) throws Exception {
		NangLuc nangLuc = new NangLuc();
		
		return nangLuc;
	}

	@Override
	public KPI getKPI(Integer kpi_id) throws Exception {
		KPI kpi = new KPI();
		
		return kpi;
	}

	@Override
	public KPI getKPIByTime(Integer user_id, Integer kpi_id) throws Exception {
		KPI kpi = new KPI();
		return kpi;
	}

	@Override
	public Skill getSkill(Integer user_id) throws Exception {

		Skill skill = new Skill ();
		return skill;
	}

	
}
