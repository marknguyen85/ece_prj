/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkt.cwp.services;

import com.hkt.cwp.Utils.Constants;
import com.hkt.cwp.Utils.ValidationUtil;
import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import com.hkt.cwp.dao.UserDao;
import com.hkt.cwp.models.Employee;
import com.hkt.cwp.models.Technique;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author HP
 */
@Service
public class TechniqueServiceImpl extends AbstractServiceBase implements TechniqueService {

    @Autowired
    private UserDao empDao;

    //Get tecnique of employee
    @Override
    public ResultBean getTechniqueById(String userId) throws MessageListException, Exception {

        resultBean = new ResultBean();
        Integer empId = ValidationUtil.validateInt(userId, lstError, Constants.USER_ID, false, false);
        Technique technique = null;
        Employee user = empDao.getById(empId);
        if (user != null) {
            technique = user.getTechnique();
        }

        if (null == technique) {
            status = HttpStatus.NO_CONTENT;
            return resultBean;
        }
        resultBean.setResult(Constants.RESULT_SUCCESS);
        resultBean.setMessage(Constants.MSG_SUCCESS);
        resultBean.setData(technique);
        status = HttpStatus.OK;
        return resultBean;
    }

}
