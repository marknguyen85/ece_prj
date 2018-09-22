/**
 * 
 */
package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.Utils.StringUtil;
import com.hkt.cwp.Utils.ValidationUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.TechniqueDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
import com.hkt.cwp.models.Technique;
import com.hkt.cwp.response.EmployeeCapacity;
import com.hkt.cwp.response.RankEmployee;
import com.hkt.cwp.response.SkillTestResponse;
import com.hkt.cwp.response.TechniqueEmp;

/**
 * @author HP
 *
 */
@Service
public class UserServiceImpl extends AbstractServiceBase implements UserService {

	@Autowired
	private transient UserDao userDao;

	@Autowired
	private transient EmployeeSkillTestDao eSTDao;

	@Autowired
	private transient TechniqueDao techDao;

	@Override
	public ResultBean getUser(String userName, String password) throws MessageListException, Exception {
		resultBean = new ResultBean();
		lstError.clear();
		Employee user = userDao.getUser(userName, password);
		if (user == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(user);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean registerUser(String jsonData) throws MessageListException, Exception {
		resultBean = new ResultBean();
		lstError.clear();
		// Get JSON Object
		JsonObject json = JsonDataUtil.getJsonObject(jsonData);

		// The JSON format is incorrect.
		if (null == json) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}

		Employee user = new Employee();
		user = (Employee) createObjecFromJson(json, user, null, null, null);

		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(user);
		status = HttpStatus.OK;
		return resultBean;
	}

	public ResultBean authority(String jsonData) throws MessageListException, Exception {
		resultBean = new ResultBean();
		// Get JSON Object
		JsonObject json = JsonDataUtil.getJsonObject(jsonData);
		lstError.clear();

		// The JSON format is incorrect.
		if (null == json) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}

		String userName = JsonDataUtil.getJsonString(json, "username");
		String password = JsonDataUtil.getJsonString(json, "password");
		Employee user = userDao.getUser(userName, password);
		if (null == user) {
			// Not found user
			status = HttpStatus.UNAUTHORIZED;
			resultBean = new ResultBean(Constants.RESULT_FAIL,
					BundleUtils.getString(Constants.ERR_ID_INTERNAL_SERVER_ERROR));
		} else {
			status = HttpStatus.OK;
			resultBean.setResult(Constants.RESULT_SUCCESS);
			resultBean.setMessage(Constants.MSG_SUCCESS);
			resultBean.setData(user);
		}

		return resultBean;

	}

	@Override
	public ResultBean getTechnique(String userId) throws MessageListException, Exception {
		lstError.clear();
		Integer empId = ValidationUtil.validateInt(userId, lstError, Constants.USER_ID, false, false);
		if (!lstError.isEmpty()) {
			throw new MessageListException(lstError);
		}

		List<SkillTestResponse> lstSkill = new ArrayList<>();
		TechniqueEmp techEmpCurent = searchRankEmp(empId, lstSkill);
		if (techEmpCurent != null && techEmpCurent.getEmployee_id() == null) {
			this.status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		Integer techId = techEmpCurent.getTechnique_id();
		Technique technique = techDao.getTechniqueById(techId);
		List<Employee> lstEmp = technique.getEmployees();
		HashMap<String, Object> mapResult = new HashMap<>();
		HashMap<String, Object> mapData = new HashMap<>();
		mapData.put("skills", lstSkill);
		List<TechniqueEmp> lstTechMoreEmp = new ArrayList<>();
		for (Employee emp : lstEmp) {
			TechniqueEmp techEmp = searchRankEmp(emp.getId(), lstSkill);
			techEmp.setSkills(lstSkill);
			lstTechMoreEmp.add(techEmp);
		}
		mapData.put("employees", lstTechMoreEmp);
		mapResult.put("data", mapData);
		status = HttpStatus.OK;
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(mapResult);
		return resultBean;
	}

	@Override
	public ResultBean getCurrentCapacity(String user_id) throws MessageListException, Exception {
		resultBean = new ResultBean();
		lstError.clear();
		Integer empId = ValidationUtil.validateInt(user_id, lstError, Constants.USER_ID, false, false);
		if (!lstError.isEmpty()) {
			throw new MessageListException(lstError);
		}
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getHistory(String user_id, String from, String to) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Date date = new Date();
		Employee employee = new Employee();
		employee = userDao.getById(Integer.parseInt(user_id));
		Integer fromDate = Integer.parseInt(from);
		Integer toDate = Integer.parseInt(to);
		List<EmployeeSkillTest> listSkillTest = new ArrayList<>();
		listSkillTest = employee.getEmployeeSkillTests();
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> listEmployeeSkillTest = new ArrayList<>();
		Integer skillId = 0;
		for (int i = 0; i < listSkillTest.size(); i++) {
			Map<String, Object> resultSkill = new HashMap<>();
			Map<String, Object> skillForMonth = new HashMap<>();
			skillId = listSkillTest.get(i).getSkill().getId();
			date = listSkillTest.get(i).getStarttime();
			if (date != null) {
				if (date.getMonth() >= fromDate && date.getMonth() <= toDate) {
					resultSkill.put("skillId", listSkillTest.get(i).getSkill().getId());
					resultSkill.put("skillName", listSkillTest.get(i).getSkill().getName());
					resultSkill.put("month", date.getMonth());
					resultSkill.put("point", listSkillTest.get(i).getPoint());
					listEmployeeSkillTest.add(resultSkill);
				}
			}
		}
		Map<String, Object> resultTech = new HashMap<>();
		result.put("skills", listEmployeeSkillTest);
		result.put("technical_id", employee.getTechnique().getId());
		result.put("technical_name", employee.getTechnique().getName());
		resultBean.setData(result);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	private TechniqueEmp searchRankEmp(Integer userId, List<SkillTestResponse> lstSkillName) throws Exception {
		StringBuilder sb = new StringBuilder(
				"select e.id employee_id, e.name employee_name, t.id technique_id, t.name technique_name, s.id skill_id, s.name skill_name, ");
		sb.append(
				"(select point from ece_system.employee_skill_test est where est.employee_id = e.id and est.skill_id = s.id order by starttime desc limit 1) point ");
		sb.append("from employee e ");
		sb.append("LEFT JOIN technique t on e.technique_id = t.id ");
		sb.append("LEFT JOIN skill s on s.technique_id = t.id ");
		sb.append("where e.id = ? ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(userId);
		TechniqueEmp tech = new TechniqueEmp();
		lstSkillName.clear();
		List<Object[]> lstResult = userDao.excuteNativeQuery(sb.toString(), paramList, null, null);
		for (int i = 0; i < lstResult.size(); i++) {
			Object[] object = lstResult.get(i);
			SkillTestResponse skillTest = new SkillTestResponse();
			String skillName = object[5] != null ? object[5].toString() : null;
			skillTest.setSkill_name(skillName);
			skillTest.setSkill_point(object[6] != null ? Integer.parseInt(object[6].toString()) : null);
			lstSkillName.add(skillTest);
			tech.setTechnique_id(object[2] != null ? Integer.parseInt(object[2].toString()) : null);
			tech.setTechnique_name(object[3] != null ? object[3].toString() : null);
			tech.setEmployee_id(userId);
			tech.setEmployee_name(object[1] != null ? object[1].toString() : null);
		}
		return tech;
	}

	
	
	private List<String> getListSkill(Integer id) throws Exception {
		List<String> lstSkill = new ArrayList<>();
		StringBuilder sb = new StringBuilder(
				"select e.id employee_id, s.id skill_id, e.name employee_name, s.name skill_name, t.name technique_name, ");
		sb.append(
				"(select point from ece_system.employee_skill_test est where est.employee_id = e.id and est.skill_id = s.id order by starttime desc limit 1) point ");
		sb.append("from employee e ");
		sb.append("LEFT JOIN technique t on e.technique_id = t.id ");
		sb.append("LEFT JOIN skill s on s.technique_id = t.id ");
		sb.append("LEFT JOIN employee_skill_test est on est.employee_id = e.id ");
		sb.append("where e.id = ? ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(id);

		List<Object[]> lstResult = userDao.excuteNativeQuery(sb.toString(), paramList, null, null);
		for (int i = 0; i < lstResult.size(); i++) {
			// Object[] object = lstResult.get(i);
			if (StringUtil.isEmpty(lstResult.get(i).toString())) {
				lstSkill.add(lstResult.get(i).toString());
			}
		}
		return lstSkill;
	}
}
