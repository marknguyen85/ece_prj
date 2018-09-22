package com.hkt.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.SkillDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.Skill;
@Service
public class SkillServiceImpl extends AbstractServiceBase implements SkillService {
	
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private UserDao userDao;
	@Override
	public ResultBean getListKPI(String kpi_id) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Skill skill = new Skill();
	//	skill = 
		
		return resultBean;
	}

}
