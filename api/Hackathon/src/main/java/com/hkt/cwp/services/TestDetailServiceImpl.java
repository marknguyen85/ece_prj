package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.EmployeeTestDetailDao;
import com.hkt.cwp.dao.SkillDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
import com.hkt.cwp.models.EmployeeTestDetail;
import com.hkt.cwp.models.Skill;
import com.hkt.cwp.models.TestDetail;

@Service

public class TestDetailServiceImpl extends AbstractServiceBase implements TestDetailService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SkillDao skillDao;
	@Autowired
	private EmployeeSkillTestDao employeeSkillTestDao;
	@Autowired
	private EmployeeTestDetailDao employeeTestDetailDao;
	@Override
	@Transactional
	public ResultBean insertTest(String json) throws MessageListException, Exception {
		resultBean = new ResultBean();
		JsonObject jsonData = JsonDataUtil.getJsonObject(json);

		// The JSON format is incorrect.
		if (null == jsonData) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}
		EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
		Employee employee = new Employee();
		Integer userId = JsonDataUtil.getJsonInteger(jsonData, "employee_id");
		Integer skillId = JsonDataUtil.getJsonInteger(jsonData, "skill_id");
		Skill skill = skillDao.getById(skillId);
		employee = userDao.getById(userId);
		employeeSkillTest.setEmployee(employee);
		employeeSkillTest.setSkill(skill);
		employeeSkillTest.setPoint(0);
		employeeSkillTest.setEmployeeTestDetails(null);

		int result = employeeSkillTestDao.insertEmployeeSkillTest(employeeSkillTest);
		EmployeeTestDetail employeeTestDetail = new EmployeeTestDetail();
		employeeTestDetail.setEmployeeSkillTest(employeeSkillTest);
		int a = employeeTestDetailDao.insertEmployeeTestDetail(employeeTestDetail);
		List<TestDetail> listTestDetail = new ArrayList<>();
		listTestDetail = skill.getTestDetails();
		Collections.shuffle(listTestDetail);
		resultBean.setData(listTestDetail);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.CREATED;
		return resultBean;
	}



}
