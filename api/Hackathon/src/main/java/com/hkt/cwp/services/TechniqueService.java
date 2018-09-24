/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hkt.cwp.services;

import com.hkt.cwp.bean.MessageListException;
import com.hkt.cwp.bean.ResultBean;
import org.springframework.http.HttpStatus;

/**
 *
 * @author HP
 */
public interface TechniqueService {

    /**
     *
     * @param userId
     * @return
     * @throws com.hkt.cwp.bean.MessageListException
     * @throws Exception
     */
    ResultBean getTechniqueById(String userId) throws MessageListException, Exception;
    HttpStatus getStatus();
}
