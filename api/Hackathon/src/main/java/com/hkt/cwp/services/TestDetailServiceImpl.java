package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.EmployeeTestDetailDao;
import com.hkt.cwp.dao.SkillDao;
import com.hkt.cwp.dao.TestDetailDao;
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
	@Autowired
	private TestDetailDao testDetailDao;

	@Override
	@Transactional
	public ResultBean insertTest(HttpServletRequest request) throws MessageListException, Exception {
		resultBean = new ResultBean();

		// The JSON format is incorrect.

		EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
		//Employee employee = new Employee();
		//Integer userId = Integer.parseInt(request.getParameter("employee_id"));
		//Integer skillId = Integer.parseInt(request.getParameter("skill_id"));
		Integer employee_skill_test_id = Integer.parseInt(request.getParameter("employee_skill_test_id"));
		employeeSkillTest = employeeSkillTestDao.getListTest(employee_skill_test_id);
		Skill skill = skillDao.getById(employeeSkillTest.getSkill().getId());
		List<TestDetail> listTestDetail = testDetailDao.getListTestDetailBySkill(employeeSkillTest.getSkill().getId());
		listTestDetail = skill.getTestDetails();
		Map<String, Object> resultdata = new HashMap<>();
		if(listTestDetail.isEmpty())
		{
			resultdata.put("employee_skill_test_id", employee_skill_test_id);
			resultdata.put("details", listTestDetail);
			resultBean.setData(resultdata);
			resultBean.setResult(Constants.RESULT_SUCCESS);
			resultBean.setMessage(Constants.MSG_SUCCESS);
			status = HttpStatus.OK;
			return resultBean;
		}
		Date date = new Date();
		employeeSkillTest.setStarttime(date);
		employeeSkillTest.setEmployeeTestDetails(null);

		int result = employeeSkillTestDao.insertEmployeeSkillTest(employeeSkillTest);
		EmployeeTestDetail employeeTestDetail = new EmployeeTestDetail();
		employeeTestDetail.setEmployeeSkillTest(employeeSkillTest);
		int a = employeeTestDetailDao.insertEmployeeTestDetail(employeeTestDetail);
		
		Collections.shuffle(listTestDetail);
		
		resultdata.put("employee_skill_test_id", result);
		resultdata.put("details", listTestDetail);
		resultBean.setData(resultdata);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}
	/**
	 * @author thuan
	 */
	@Transactional
	@Override
	public ResultBean updateTest(String json) throws MessageListException, Exception {
		resultBean = new ResultBean();
		JsonObject jsonData = JsonDataUtil.getJsonObject(json);
		lstError.clear();

		// The JSON format is incorrect.
		if (null == json) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}
		String user_Id = JsonDataUtil.getJsonString(jsonData, "user_id");
		String skill_id = JsonDataUtil.getJsonString(jsonData, "skill_id");
		String employee_skill_test_id = JsonDataUtil.getJsonString(jsonData, "employee_skill_test_id");
		
		JsonArray jsonarray = jsonData.getAsJsonArray("details");
		TestDetail testDetail = new TestDetail();
		
		
		EmployeeSkillTest employeeSkillTest = new EmployeeSkillTest();
		EmployeeTestDetail employeeTestDetail = new EmployeeTestDetail();
		employeeSkillTest = employeeSkillTestDao.getListTest(Integer.parseInt(employee_skill_test_id));
		String test_detail_id = null;
		String answer = null;
		int point = 0;
		for (int i = 0; i < jsonarray.size(); i++) {
			JsonObject jsonTest = jsonarray.get(i).getAsJsonObject();
			test_detail_id = JsonDataUtil.getJsonString(jsonTest, "test_detail_id");
			answer = JsonDataUtil.getJsonString(jsonTest, "answer");
			testDetail = testDetailDao.getById(Integer.parseInt(test_detail_id));
			if (answer.equals(Integer.toString(testDetail.getCorrectAnswer()))) {
				point+=10;
				employeeTestDetail.setIsCorrect(1);
			}
			employeeTestDetail.setAnswer1(testDetail.getAnswer1());
			employeeTestDetail.setAnswer2(testDetail.getAnswer2());
			employeeTestDetail.setAnswer3(testDetail.getAnswer3());
			employeeTestDetail.setAnswer4(testDetail.getAnswer4());
			employeeTestDetail.setAnswer5(testDetail.getAnswer5());
			employeeTestDetail.setChoice(Integer.parseInt(answer));
			employeeTestDetail.setCorrectAnswer(testDetail.getCorrectAnswer());
			employeeTestDetail.setQuestion(testDetail.getQuestion());
			employeeTestDetail.setEmployeeSkillTest(employeeSkillTest);
			employeeTestDetail.setIsCorrect(0);
			int a = employeeTestDetailDao.updateEmployeeTestDetail(employeeTestDetail);
		}
		employeeSkillTest.setPoint(point);
		int result = employeeSkillTestDao.updateEmployeeSkillTest(employeeSkillTest);
		Date date = new Date();
		employeeSkillTest.setStarttime(date);
		employeeSkillTest.setEmployeeTestDetails(null);
		Map<String, Object> resultdata = new HashMap<>();
		resultdata.put("point", point);
		resultBean.setData(resultdata);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

}
