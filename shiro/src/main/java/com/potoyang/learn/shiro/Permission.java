package com.potoyang.learn.shiro;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 15:04
 * Modified By:
 * Description:
 */
public class Permission implements Serializable {
    private static final long serialVersionUID = 6972164796080338099L;

    private Integer id;
    private String permissionName;

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
}
