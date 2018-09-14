package com.potoyang.learn.shirojwt.entity;


/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:22:42
 * Modified By:
 * Description:
 */
public class SysRolePermission {
    
    private Integer roleId;
    
    private Integer permissionId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

}