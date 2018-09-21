package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.BaiThi;
import com.hkt.cwp.models.ChiTietBaiThi;
import com.hkt.cwp.models.KPI;
import com.hkt.cwp.models.NangLuc;
import com.hkt.cwp.models.Skill;
import com.hkt.cwp.models.User;

@Service
public class User1ServiceImpl extends AbstractServiceBase implements User1Service {

	@Autowired
	private transient UserDao userDao;

	@Override
	public ResultBean getUserInfo(String user_id) throws MessageListException, Exception {
		Integer userId = Integer.parseInt(user_id);
		User user = userDao.getUserInfo(userId);
		resultBean.setData(user);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getListTest(String user_id) throws MessageListException, Exception {
		List<BaiThi> listTest = new ArrayList<>();
		Integer userId = Integer.parseInt(user_id);
		listTest = userDao.getListTest(userId);
		resultBean.setData(listTest);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getTestDetail(String test_id) throws MessageListException, Exception {
		ChiTietBaiThi chiTietBaiThi = new ChiTietBaiThi();
		Integer testId = Integer.parseInt(test_id);
		chiTietBaiThi = userDao.getTestDetail(testId);
		resultBean.setData(chiTietBaiThi);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getAbility(String user_id) throws MessageListException, Exception {
		NangLuc nangLuc = new NangLuc();
		Integer userId = Integer.parseInt(user_id);
		nangLuc = userDao.getAbility(userId);
		resultBean.setData(nangLuc);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getKPI(String kpi_id) throws MessageListException, Exception {
		KPI kpi = new KPI();
		Integer kpiId = Integer.parseInt(kpi_id);
		kpi = userDao.getKPI(kpiId);
		resultBean.setData(kpi);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getKPIByTime(String user_id, String kpi_id) throws MessageListException, Exception {
		KPI kpi = new KPI();
		Integer userId = Integer.parseInt(user_id);
		Integer kpiId = Integer.parseInt(kpi_id);
		kpi = userDao.getKPIByTime(userId, kpiId);
		resultBean.setData(kpi);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;
	}

	@Override
	public ResultBean getSkill(String user_id) throws MessageListException, Exception {
		Skill skill = new Skill();
		Integer userId = Integer.parseInt(user_id);
		skill = userDao.getSkill(userId);
		resultBean.setData(skill);
		resultBean.setResult(Constants.RESULT_SUCCESS);
		resultBean.setMessage(Constants.MESSAGE_SUCCESS);
		status = HttpStatus.OK;
		return resultBean;

	}

}
