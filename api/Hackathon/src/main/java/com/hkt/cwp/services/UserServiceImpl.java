/**
 * 
 */
package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.hkt.cwp.Utils.BundleUtils;
import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.DataUtils;
import com.hkt.cwp.Utils.JsonDataUtil;
import com.hkt.cwp.Utils.StringUtil;
import com.hkt.cwp.Utils.ValidationUtil;
import com.hkt.cwp.bean.ErrorBean;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.TechniqueDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.EmployeeSkillTest;
import com.hkt.cwp.models.Technique;
import com.hkt.cwp.response.EmployeeCapacity;
import com.hkt.cwp.response.EmployeeSkillTestResponse;
import com.hkt.cwp.response.HistoryEmployee;
import com.hkt.cwp.response.RankEmployee;
import com.hkt.cwp.response.SkillHistory;
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
		if (techEmpCurent == null) {
			this.status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		Integer techId = techEmpCurent.getTechnique_id();
		Technique technique = techDao.getTechniqueById(techId);
		List<Employee> lstEmp = technique.getEmployees();
		HashMap<String, Object> mapData = new HashMap<>();
		mapData.put("skills", lstSkill);
		List<TechniqueEmp> lstTechMoreEmp = new ArrayList<>();
		for (Employee emp : lstEmp) {
			List<SkillTestResponse> lstSkillEmp = new ArrayList<>();
			TechniqueEmp techEmp = searchRankEmp(emp.getId(), lstSkillEmp);
			if (techEmp == null) {
				continue;
			}
			techEmp.setSkills(lstSkillEmp);
			lstTechMoreEmp.add(techEmp);
		}
		lstTechMoreEmp.sort(Comparator.comparing(TechniqueEmp::getSumPoint).reversed());
		for (int i = 0; i < lstTechMoreEmp.size(); i++ ) {
			TechniqueEmp obj= lstTechMoreEmp.get(i);
			obj.setRank(i + 1);
		}
		mapData.put("employees", lstTechMoreEmp);
		status = HttpStatus.OK;
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		resultBean.setData(mapData);
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
		List<SkillTestResponse> lstSkill = new ArrayList<>();
		TechniqueEmp techEmpCurent = searchRankEmp(empId, lstSkill);
		if (techEmpCurent != null && techEmpCurent.getEmployee_id() == null) {
			this.status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		techEmpCurent.setSkills(lstSkill);
		resultBean.setData(techEmpCurent);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getHistory(String user_id, String from, String to) throws MessageListException, Exception {
		resultBean = new ResultBean();
		Integer empId = ValidationUtil.validateInt(user_id, lstError, Constants.USER_ID, false, false);
		Date fromDate = null, toDate = null;
		if (!StringUtil.isEmpty(from)) {
			fromDate = DataUtils.convertStringToDate(from);
		}
		if (!StringUtil.isEmpty(to)) {
			toDate = DataUtils.convertStringToDate(to);
		}
		if (from == null && toDate == null) {
			lstError.add(new ErrorBean("1", "from", "from is bank"));
		}
		if (null != fromDate) {
			from = DataUtils.convertDateToString(Constants.YYYYMMDD_WITH_HYPHEN_FORMAT, fromDate);
		}
		
		if (null != toDate) {
			to = DataUtils.convertDateToString(Constants.YYYYMMDD_WITH_HYPHEN_FORMAT, toDate);
		}
		
		if (!lstError.isEmpty()) {
			throw new MessageListException(lstError);
		}
		Map<String, List<EmployeeSkillTestResponse>> mapSkillTest = new HashMap<>();
		HistoryEmployee history = searchHistory(empId, mapSkillTest, from, to);
		if (history == null) {
			this.status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		List<SkillHistory> lstSkills = new ArrayList<>();
		Iterator<Entry<String, List<EmployeeSkillTestResponse>>> iterator = mapSkillTest.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, List<EmployeeSkillTestResponse>> entry = iterator.next();
			SkillHistory skillHistory = new SkillHistory();
			skillHistory.setSkill_name(entry.getKey());
			skillHistory.setSkillForMonth(entry.getValue());
			lstSkills.add(skillHistory);
		}
		history.setSkills(lstSkills);
		resultBean.setData(history);
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
		TechniqueEmp tech = null;
		lstSkillName.clear();
		Integer sumPoint = 0;
		List<Object[]> lstResult = userDao.excuteNativeQuery(sb.toString(), paramList, null, null);
		for (int i = 0; i < lstResult.size(); i++) {
			tech = new TechniqueEmp();
			Object[] object = lstResult.get(i);
			SkillTestResponse skillTest = new SkillTestResponse();
			String skillName = object[5] != null ? object[5].toString() : null;
			Integer point = object[6] != null ? Integer.parseInt(object[6].toString()) : 0;
			if (sumPoint != null) {
				sumPoint += point;
			}
			skillTest.setSkill_name(skillName);
			skillTest.setSkill_point(point);
			lstSkillName.add(skillTest);
			tech.setTechnique_id(object[2] != null ? Integer.parseInt(object[2].toString()) : null);
			tech.setTechnique_name(object[3] != null ? object[3].toString() : null);
			tech.setEmployee_id(userId);
			tech.setEmployee_name(object[1] != null ? object[1].toString() : null);
			tech.setSumPoint(sumPoint);
		}
		return tech;
	}

	@Override
	public ResultBean getLocation(String user_id) throws MessageListException, Exception {
		lstError.clear();
		Employee employee = new Employee();
		Integer id = Integer.parseInt(user_id);
		if(id.equals(null))
		{
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_NULL_OR_BLANK));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}
		employee = userDao.getById(id);
		if(employee==null)
		{
			status =HttpStatus.NO_CONTENT;
			return resultBean;
		}
		String role = employee.getRole().getLocation();
		String nameRole = employee.getRole().getName();
		String []path = role.split(";");
		
		String x = path[0];
		String y = path[1];
		Map<String, Object> result = new HashMap<>();
		result.put("employee_id", employee.getId());
		result.put("employee_name", employee.getName());
		result.put("name", nameRole);
		result.put("toado_x", x);
		result.put("toado_y", y);
		resultBean.setData(result);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	
	private HistoryEmployee searchHistory(Integer userId, Map<String, List<EmployeeSkillTestResponse>> mapSkillTest, String from, String to) throws Exception {
		List<Object> paramList = new ArrayList<>();
		paramList.add(userId);
		StringBuilder sb = new StringBuilder(
				"select e.id employee_id, e.name employee_name, t.id technique_id, t.name technique_name, s.id skill_id, s.name skill_name, est.point, month(est.starttime) ");
		sb.append("from employee e ");
		sb.append("LEFT JOIN technique t on e.technique_id = t.id  ");
		sb.append("LEFT JOIN skill s on s.technique_id = t.id ");
		sb.append("LEFT JOIN ece_system.employee_skill_test est on est.employee_id = e.id   and est.skill_id = s.id "); 
		sb.append("where e.id = ? ");
		if (!StringUtil.isEmpty(from)) {
			sb.append("and starttime >= ? ");
			paramList.add(from);
		}
		if (!StringUtil.isEmpty(to)) {
			sb.append("and starttime <= ? ");
			paramList.add(to);
		}
		List<Object[]> lstResult = userDao.excuteNativeQuery(sb.toString(), paramList, null, null);
		HistoryEmployee history = null;
		List<EmployeeSkillTestResponse> lstSKTR = new ArrayList<>();
		for (int i = 0; i < lstResult.size(); i++) {
                    history = new HistoryEmployee();
			Object[] object = lstResult.get(i);
			Integer employeeId = object[0] != null ? Integer.parseInt(object[0].toString()) : null;
			String employeeName = object[1] != null ? object[1].toString() : null;
			Integer techniqueid = object[2] != null ? Integer.parseInt(object[2].toString()) : null;
			String techniqueName = object[3] != null ? object[3].toString() : null;
			Integer skillId = object[4] != null ? Integer.parseInt(object[4].toString()) : null;
			String skillName = object[5] != null ? object[5].toString() : null;
			Integer point = object[6] != null ? Integer.parseInt(object[6].toString()) : null;
			Integer month = object[7] != null ? Integer.parseInt(object[7].toString()) : null;
			EmployeeSkillTestResponse sKTR = new EmployeeSkillTestResponse();
			sKTR.setPoint(point);
			sKTR.setMonth(month);
			lstSKTR.add(sKTR);
			SkillHistory skillHistory = new SkillHistory();
			skillHistory.setSkill_name(skillName);
			skillHistory.setSkillForMonth(lstSKTR);
			mapSkillTest.put(skillName, lstSKTR);
			//HistoryEmployee
			history.setId(employeeId);
			history.setName(employeeName);
			history.setTechnique_id(techniqueid);
			history.setTechnique_name(techniqueName);
		}
		return history;
	}
}
