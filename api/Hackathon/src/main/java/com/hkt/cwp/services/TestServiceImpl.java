package com.hkt.cwp.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.bean.response.TestResponse;
import com.hkt.cwp.models.Test;

@Service
public class TestServiceImpl implements TestService {
	@PersistenceContext
	private EntityManager entityManager;
	private ResultBean resultBean;
	@SuppressWarnings("unchecked")
	@Override
	public ResultBean getAllTest() {
		resultBean = new ResultBean();
		String sql = " FROM Test";
		List<Test> list = new ArrayList<>();
		list = entityManager.createQuery(sql).getResultList();
		resultBean.setData(list);
		resultBean.setMessage("Success");
		return resultBean;
	}
	
}
