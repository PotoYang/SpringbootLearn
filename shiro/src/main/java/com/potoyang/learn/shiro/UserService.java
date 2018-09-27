package com.potoyang.learn.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 14:51
 * Modified By:
 * Description:
 */
@Service
public class UserService {

    public List<Role> getRoleListByUserId(Integer userId) {
        List<Role> roleList = new ArrayList<>();
        Role role;
        for (int i = 1; i < 2; i++) {
            role = new Role();
            role.setId(i);
            role.setRoleName(userId + " to " + i);
            role.setDescription(i + " is " + i);
            roleList.add(role);
        }
        return roleList;
    }

    public List<Permission> getPermissionListByRoleId(Integer roleId) {
        List<Permission> permissionList = new ArrayList<>();
        Permission permission;
        for (int i = 10; i < 12; i++) {
            permission = new Permission();
            permission.setId(i);
            permission.setPermissionName(roleId + " to " + i);
            permissionList.add(permission);
        }

        return permissionList;
    }

    public User findByUsername(String username) {
        User user = new User();
        switch (username) {
            case "123":
                user.setId(1);
                user.setUsername(username);
                user.setPassword(encryptPassword(username));
                break;
            case "456":
                user.setId(2);
                user.setUsername(username);
                user.setPassword(encryptPassword(username));
                break;
            default:
                break;
        }
        return user;
    }

    private String encryptPassword(String password) {
//        String salt =.nextBytes().toHex();
        //String newPassword = new SimpleHash(algorithmName, user.getPassword()).toHex();
        String algorithmName = "md5";
        int hashIterations = 2;
        return new SimpleHash(algorithmName, password, ByteSource.Util.bytes("123"), hashIterations).toHex();
//        return new SimpleHash(algorithmName, password, hashIterations).toHex();
    }
}
