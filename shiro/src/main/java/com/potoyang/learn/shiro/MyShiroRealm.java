package com.potoyang.learn.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 14:37
 * Modified By:
 * Description:
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        try {
            String username = principals.toString();

            User user = userService.findByUsername(username);
            for (Role role : userService.getRoleListByUserId(user.getId())) {
                System.out.println(role.getRoleName());
                authorizationInfo.addRole(role.getRoleName());
                for (Permission permission : userService.getPermissionListByRoleId(role.getId())) {
                    System.out.println(permission.getPermissionName());
                    authorizationInfo.addStringPermission(permission.getPermissionName());
                }
            }
            return authorizationInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findByUsername(username);
        if (user == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                username,
                user.getPassword(),
                ByteSource.Util.bytes("123"),
                getName()
        );
    }
}
