package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.List;

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

@Service
public class EmployeeSkillTestServiceImpl extends AbstractServiceBase implements EmployeeSkillTestService {

	@Autowired
	private EmployeeSkillTestDao employeeSkillTestDao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public ResultBean getListTest(String user_id) throws MessageListException, Exception {

		if (user_id == null) {
			resultBean = new ResultBean(Constants.RESULT_FAIL, BundleUtils.getString(Constants.ERR_ID_FORMAT_JSON));
			status = HttpStatus.BAD_REQUEST;
			return resultBean;
		}
		List<EmployeeSkillTest> listEmployeeSkillTest = new ArrayList<>();
		Integer userId = Integer.parseInt(user_id);
		Employee employee = new Employee();
		employee = userDao.getById(Integer.parseInt(user_id));
		if (employee == null) {
			status = HttpStatus.NO_CONTENT;
			return resultBean;
		}
		
		//get Employee
		employee = userDao.getById(Integer.parseInt(user_id));
		listEmployeeSkillTest = employee.getEmployeeSkillTests();
		
		resultBean.setData(listEmployeeSkillTest);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MSG_SUCCESS);
		status = HttpStatus.OK;

		return resultBean;
	}

}
