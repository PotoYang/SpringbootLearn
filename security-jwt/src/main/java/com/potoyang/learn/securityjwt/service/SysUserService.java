package com.potoyang.learn.securityjwt.service;

import com.potoyang.learn.securityjwt.entity.SysUser;
import com.potoyang.learn.securityjwt.entity.SysUserRole;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:11
 * Modified By:
 * Description:
 */
public interface SysUserService {
    List<String> getAllUrl();

    /**
     * 获取角色名称
     *
     * @param url
     * @return
     */
    List<String> getRoleNameByUrl(String url);

    /**
     * 根据loginName获取用户角色
     *
     * @param loginName
     * @return
     */
    List<SysUserRole> getRoleByLoginName(String loginName);

    /**
     * 根据Id获取SysUser
     *
     * @param userId
     * @return
     */
    SysUser getSysUserById(Integer userId);

    /**
     * 根据role_id获取roleName
     *
     * @param roleId
     * @return
     */
    String getRoleNameById(Integer roleId);

    /**
     * 根据登录信息获取用户
     *
     * @param loginName
     * @param userPassword
     * @return
     */
    SysUser getSysUser(String loginName, String userPassword);

    String queryRole();
}
