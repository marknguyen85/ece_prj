package com.hkt.cwp.services;

import java.util.ArrayList;
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
import com.hkt.cwp.dao.SkillDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
import com.hkt.cwp.models.Skill;

@Service
public class SkillServiceImpl extends AbstractServiceBase implements SkillService {

	@Autowired
	private SkillDao skillDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EmployeeSkillTestDao employeeSkillTestDao;

	@Override
	public ResultBean getListKPI(String kpi_id, String starttime) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Skill skill = new Skill();
		// skill =
		if (kpi_id == null) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}
		Integer skill_id = Integer.parseInt(kpi_id);
		skill = skillDao.getById(skill_id);
		if (skill == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		
		List<Map<String, Object>> listEmployeeSkillTest = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		for (int i=0;i<skill.getEmployeeSkillTests().size();i++)
		{
			EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
			employeeSkillTest = skill.getEmployeeSkillTests().get(i);
			if(employeeSkillTest.getStarttime()==null)
			{
				continue;
			}
			Map<String, Object> resultSkillTest = new HashMap<>();
			resultSkillTest.put("employee_id", employeeSkillTest.getEmployee().getId());
			resultSkillTest.put("employee_name", employeeSkillTest.getEmployee().getName());
			resultSkillTest.put("point", employeeSkillTest.getPoint());
			resultSkillTest.put("starttime", employeeSkillTest.getStarttime());
			listEmployeeSkillTest.add(resultSkillTest);
		}
		result.put("skill_id", skill.getId());
		result.put("skill_name", skill.getName());
		result.put("employee_skill_test",listEmployeeSkillTest);
		resultBean.setData(result);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getListKPIByTime(String user_id, String kpi_id) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Employee employee = new Employee();
		employee = userDao.getById(Integer.parseInt(user_id));
		Integer kpiId = Integer.parseInt(kpi_id);
		List<EmployeeSkillTest> listSkill = employee.getEmployeeSkillTests();
		List<Map<String, Object>> listEmployeeSkillTest = new ArrayList<>();
		String name = null;
		for (int i =0 ;i<listSkill.size();i++)
		{
			Map<String, Object> result = new HashMap<>();
			Skill skill = new Skill();
			skill = listSkill.get(i).getSkill();
			if(skill.getId() == kpiId)
			{
				name = skill.getName();
				result.put("point", listSkill.get(i).getPoint());
				result.put("starttime", listSkill.get(i).getStarttime());
				listEmployeeSkillTest.add(result);
			}
		}
		Map<String, Object> result1 = new HashMap<>();
		result1.put("employee_id",employee.getId());
		result1.put("skill_id",kpiId);
		result1.put("employee_name", employee.getName());
		result1.put("skill_name", name);
		result1.put("info", listEmployeeSkillTest);
		resultBean.setData(result1);
		
		
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getListKPINotDone(String kpi_id, String starttime) throws MessageListException, Exception {
		
		return resultBean;
	}

}
