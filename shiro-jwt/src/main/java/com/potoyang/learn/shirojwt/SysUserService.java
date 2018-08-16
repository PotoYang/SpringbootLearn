package com.potoyang.learn.shirojwt;

import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 11:54
 * Modified By:
 * Description:
 */
@Service
public class SysUserService {

    public SysUser findByUsername(String username) {
        SysUser sysUser = new SysUser();
        switch (username) {
            case "123":
                sysUser.setUsername(username);
                sysUser.setPassword("456");
                sysUser.setRole("user");
                sysUser.setPermission("view");
                break;
            case "456":
                sysUser.setUsername(username);
                sysUser.setPassword("456");
                sysUser.setRole("admin");
                sysUser.setPermission("view,edit");
                break;
            default:
                break;
        }

        return sysUser;
    }

}
