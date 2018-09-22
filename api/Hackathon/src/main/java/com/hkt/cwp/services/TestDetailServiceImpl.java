package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	public ResultBean insertTest(HttpServletRequest request) throws MessageListException, Exception {
		resultBean = new ResultBean();

		// The JSON format is incorrect.
		
		EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
		Employee employee = new Employee();
		Integer userId = Integer.parseInt(request.getParameter("employee_id"));
		Integer skillId = Integer.parseInt(request.getParameter( "skill_id"));
		Skill skill = skillDao.getById(skillId);
		employee = userDao.getById(userId);
		employeeSkillTest.setEmployee(employee);
		employeeSkillTest.setSkill(skill);
		employeeSkillTest.setPoint(0);
		Date date = new Date();
		employeeSkillTest.setStarttime(date);
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
	@Override
	public ResultBean updateTest(HttpServletRequest request) throws MessageListException, Exception {
		resultBean = new ResultBean();
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.CREATED;
		return resultBean;
	}



}
