package com.potoyang.learn.securityjwt.service.impl;

import com.potoyang.learn.securityjwt.entity.SysPermission;
import com.potoyang.learn.securityjwt.entity.SysUser;
import com.potoyang.learn.securityjwt.entity.SysUserRole;
import com.potoyang.learn.securityjwt.mapper.SysPermissionMapper;
import com.potoyang.learn.securityjwt.mapper.SysRoleMapper;
import com.potoyang.learn.securityjwt.mapper.SysUserMapper;
import com.potoyang.learn.securityjwt.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:12
 * Modified By:
 * Description:
 */
@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);
    private static final String TAG = SysUserServiceImpl.class.getSimpleName();

    private final SysUserMapper sysUserMapper;
    private final SysRoleMapper sysRoleMapper;
    private final SysPermissionMapper sysPermissionMapper;

    private List<String> roleNameList = new ArrayList<>();

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper, SysRoleMapper sysRoleMapper, SysPermissionMapper sysPermissionMapper) {
        this.sysUserMapper = sysUserMapper;
        this.sysRoleMapper = sysRoleMapper;
        this.sysPermissionMapper = sysPermissionMapper;
    }

    @Override
    public List<String> getAllUrl() {
        return sysPermissionMapper.selectPermissionUrlAll();
    }

    @Override
    public List<String> getRoleNameByUrl(String url) {
        List<String> rlist = sysPermissionMapper.selectPermissionRoleNameByUrl(url);
        SysPermission permission = sysPermissionMapper.selectPermissionByUrl(url);

        if (permission != null) {
            roleNameList.clear();
            getRoleNames(permission.getId());
            rlist.addAll(roleNameList);
        }
        return rlist;
    }

    private void getRoleNames(int id) {
        List<SysPermission> sysPermissions = sysPermissionMapper.selectSysPermissionsByParentId(id);
        if (sysPermissions != null && sysPermissions.size() > 0) {
            for (SysPermission sysPermission : sysPermissions) {
                List<String> list = sysPermissionMapper.selectPermissionRoleNameByUrl(sysPermission.getPermissionUrl());
                roleNameList.addAll(list);
                getRoleNames(sysPermission.getId());
            }
        }
    }

    @Override
    public List<SysUserRole> getRoleByLoginName(String loginName) {
        SysUser sysUser = sysUserMapper.selectByLoginName(loginName);
        if (sysUser == null) {
            return null;
        }
        LOGGER.info(TAG + " sysUser == null : " + false);
        List<SysUserRole> sysUserRoleList = sysRoleMapper.selectByUserId(sysUser.getId());
        LOGGER.info(TAG + " roles.size : " + sysUserRoleList.size());
        return sysUserRoleList;
    }

    @Override
    public SysUser getSysUserById(Integer id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public String getRoleNameById(Integer id) {
        return sysRoleMapper.selectRoleNameById(id);
    }

    @Override
    public SysUser getSysUser(String loginName, String userPassword) {
        List<SysUser> sysUsers = sysUserMapper.selectUserByLoginNameAndUserPassword(loginName, userPassword);
        if (sysUsers != null && sysUsers.size() > 0) {
            return sysUsers.get(0);
        }
        return null;
    }

    @Override
    public String queryRole() {
        return "123";
    }
}