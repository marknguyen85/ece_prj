package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
import com.hkt.cwp.models.Skill;

@Service
public class EmployeeSkillTestServiceImpl extends AbstractServiceBase implements EmployeeSkillTestService {

	@Autowired
	private EmployeeSkillTestDao employeeSkillTestDao;
	@Autowired
	private UserDao userDao;

	@Override
	public ResultBean getListTest(String user_id,String starttime) throws MessageListException, Exception {

		if (user_id == null) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}

		Integer userId = Integer.parseInt(user_id);
		Employee employee = new Employee();
		// get Employee
		employee = userDao.getById(userId);
		if (employee == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		Integer a = Integer.parseInt(starttime);
		if(a.equals(1))
		{
			Map<String, Object> result = new HashMap<>();
			List<Map<String, Object>> listEmployeeSkillTest = new ArrayList<>();
			Date date = new Date();
			for (int i = 0; i < employee.getEmployeeSkillTests().size(); i++) {
				Map<String, Object> mapResult = new HashMap<>();
				Map<String, Object> mapSkill = new HashMap<>();
				//Map<String, Object> mapTechical = new HashMap<>();
				Skill skill = new Skill();
				skill = employee.getEmployeeSkillTests().get(i).getSkill();
				Integer id = employee.getEmployeeSkillTests().get(i).getId();
				Integer point = employee.getEmployeeSkillTests().get(i).getPoint();
				date = employee.getEmployeeSkillTests().get(i).getStarttime();
				if(date==null)
				{
					continue;
				}
				mapSkill.put("id", skill.getId());
				mapSkill.put("name", skill.getName());
				
				mapResult.put("employee_skill_test_id", id);
				mapResult.put("point", point);
				mapResult.put("skill", mapSkill);
				mapResult.put("starttime",employee.getEmployeeSkillTests().get(i).getStarttime());
				listEmployeeSkillTest.add(mapResult);
			}
			result.put("employee_id", employee.getId());
			result.put("employee_name", employee.getName());
			result.put("employee_skill_test", listEmployeeSkillTest);
			result.put("techinque", employee.getTechnique());
			resultBean.setData(result);
			resultBean.setResult(Constants.RESULT_SUCCESS);
			resultBean.setMessage(Constants.MSG_SUCCESS);
			status = HttpStatus.OK;

			return resultBean;
		}
		return getListNotTest(user_id);
	}


	private ResultBean getListNotTest(String user_id) throws MessageListException, Exception {
		if (user_id == null) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}

		Integer userId = Integer.parseInt(user_id);
		Employee employee = new Employee();
		// get Employee
		employee = userDao.getById(userId);
		if (employee == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> listEmployeeSkillTest = new ArrayList<>();
		for (int i = 0; i < employee.getEmployeeSkillTests().size(); i++) {
			Map<String, Object> mapResult = new HashMap<>();
			Map<String, Object> mapSkill = new HashMap<>();
			//Map<String, Object> mapTechical = new HashMap<>();
			Skill skill = new Skill();
			skill = employee.getEmployeeSkillTests().get(i).getSkill();
			if(employee.getEmployeeSkillTests().get(i).getStarttime()==null)
			{
				mapSkill.put("id", skill.getId());
				mapSkill.put("name", skill.getName());
				mapResult.put("skill", mapSkill);
				listEmployeeSkillTest.add(mapResult);
			}
		}
		result.put("employee_id", employee.getId());
		result.put("employee_name", employee.getName());
		result.put("employee_skill_test", listEmployeeSkillTest);
		result.put("techinque", employee.getTechnique());
		resultBean.setData(result);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;

		return resultBean;
	}

}
