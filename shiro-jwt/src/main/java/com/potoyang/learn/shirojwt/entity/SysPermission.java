package com.potoyang.learn.shirojwt.entity;


/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:22:05
 * Modified By:
 * Description:
 */
public class SysPermission {
    
    private Integer id;
    
    private String permissionName;
    
    private String permissionUrl;
    
    private Integer parentId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionUrl() {
        return permissionUrl;
    }

    public void setPermissionUrl(String permissionUrl) {
        this.permissionUrl = permissionUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

}