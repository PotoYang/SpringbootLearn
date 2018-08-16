package com.potoyang.learn.shirojwt;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 12:11
 * Modified By:
 * Description:
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 3820007927786170304L;

    private String username;
    private String password;
    private String role;
    private String permission;

    public SysUser() {
    }

    public SysUser(String username, String password, String role, String permission) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.permission = permission;
    }
}
