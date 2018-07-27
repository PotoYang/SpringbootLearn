package com.potoyang.learn.securityoauth2.service.impl;

import com.potoyang.learn.securityoauth2.dao.ApplyDao;
import com.potoyang.learn.securityoauth2.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyDao applyDao;

    @Override
    public Map findApplyById(String id) {
        return applyDao.findApplyById(id);
    }
}
