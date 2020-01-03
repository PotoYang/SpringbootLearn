package com.potoyang.learn.shirojwt.entity;


/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:23:12
 * Modified By:
 * Description:
 */
public class SysUserRole {
    
    private Integer userId;
    
    private Integer roleId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}