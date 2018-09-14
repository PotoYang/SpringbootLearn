package com.potoyang.learn.shirojwt.service.impl;

import com.potoyang.learn.shirojwt.mapper.SysRoleMapper;
import com.potoyang.learn.shirojwt.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:22:13
 * Modified By:
 * Description:
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public String getRolesByUserId(Integer userId) {
        List<String> roleNameList = sysRoleMapper.selectSysRoleNameByUserId(userId);
        return StringUtils.collectionToDelimitedString(roleNameList, "|");
    }
}