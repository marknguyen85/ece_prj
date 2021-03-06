/**
 * 
 */
package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.StringUtil;
import com.hkt.cwp.Utils.ValidationUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.EmployeeSkillTestDao;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.response.RankEmployee;

/**
 * @author CaoTT
 *
 */
@Service
public class RankServiceImpl extends AbstractServiceBase implements RankService{

	@Autowired
	private transient UserDao userDao;
	
	@Autowired
	private transient EmployeeSkillTestDao eSTDao;
	
	@Override
	public ResultBean searchEmp(String key, String page, String skillIdStr) throws MessageListException, Exception {
		resultBean = new ResultBean();
		lstError.clear();
		int offset = 0;
		List<Employee> lstEmp = new ArrayList<Employee>();
		if (!StringUtil.isEmpty(page)) {
			offset = Integer.parseInt(page) * Constants.LIMIT -1; 
		}
		lstEmp = userDao.searchEmp(key, offset);
		if (StringUtils.isEmpty(skillIdStr)) {
			lstEmp = userDao.searchEmp(key, offset);
			resultBean.setData(lstEmp);
		} else {
			//searh rank
			Integer skillId = ValidationUtil.validateInt(skillIdStr, lstError, "skill_id", false, false);
			if (!lstError.isEmpty()) {
				throw new MessageListException(lstError);
			}
			HashMap<Integer, RankEmployee> mapRankEmp = searchRankEmp(skillId);
			List<RankEmployee> lstResult = new ArrayList<>();
			for (Employee emp : lstEmp) {
				if (mapRankEmp.containsKey(emp.getId())) {
					lstResult.add(mapRankEmp.get(emp.getId()));
				}
			}
			resultBean.setData(lstResult);
		}
		if (lstEmp == null || lstEmp.size() == 0) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	private HashMap<Integer, RankEmployee> searchRankEmp(Integer skillId) throws Exception{
		StringBuilder sb = new StringBuilder("select est.employee_id, e.name as employee_name, e.code, est.skill_id, s.name as skill_name, max(est.point) point ");
		sb.append("from employee_skill_test est ");
		sb.append("LEFT JOIN employee e on e.id = est.employee_id ");
		sb.append("LEFT JOIN ece_system.skill s on s.id = est.skill_id ");
		sb.append("where est. skill_id = ? ");
		sb.append("group by est.employee_id ");
		sb.append("order by point desc ; ");
		List<Object> paramList = new ArrayList<>();
		paramList.add(skillId);
		
		HashMap<Integer, RankEmployee> map = new HashMap<Integer, RankEmployee>();
		List<Object[]> lstResult = eSTDao.excuteNativeQuery(sb.toString(), paramList, null, null);
		for (int i= 0; i < lstResult.size(); i++) {
			Object[] object = lstResult.get(i);
			RankEmployee rE = new RankEmployee();
			Integer empId = object[0] != null ? Integer.parseInt(object[0].toString()) : null;
			if (null == empId) continue;
			rE.setRank(i + 1);
			rE.setEmployee_id(empId);
			rE.setEmployy_name(object[1] != null ? object[1].toString() : null);
			rE.setCode(object[2] != null ? object[1].toString() : null);
			rE.setSkill_id(object[3] != null ? Integer.parseInt(object[3].toString()) : null);
			rE.setSkill_name(object[4] != null ? object[4].toString() : null);
			rE.setMax_point(object[5] != null ? Integer.parseInt(object[5].toString()) : null);
			map.put(empId, rE);
        }
		return map;
	}
}
