package com.potoyang.learn.shirojwt.shiro;

import com.potoyang.learn.shirojwt.Log;
import com.potoyang.learn.shirojwt.entity.SysUser;
import com.potoyang.learn.shirojwt.jwt.JwtToken;
import com.potoyang.learn.shirojwt.jwt.JwtUtil;
import com.potoyang.learn.shirojwt.mapper.SysPermissionMapper;
import com.potoyang.learn.shirojwt.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ShiroRealm extends AuthorizingRealm {

    private static final String TAG = ShiroRealm.class.getSimpleName();

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Log.i(TAG, "enter doGetAuthorizationInfo");
        // 得到的是登录名
        String loginName = JwtUtil.getUsername(principals.toString());

        Set<String> permissionSet = sysPermissionMapper.selectPermissionUrlByLoginName(loginName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissionSet);

        return simpleAuthorizationInfo;
    }

    /**
     * 验证用户名正确与否
     *
     * @param auth
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        Log.i(TAG, "enter doGetAuthenticationInfo");
        String token = (String) auth.getCredentials();
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token无效");
        }
        SysUser user = sysUserService.getUser(username);
        if (user == null) {
            throw new AuthenticationException("用户不存在");
        }
        if (user.getEnabled() == 0) {
            throw new LockedAccountException("账户锁定，请联系管理员");
        }
        if (!JwtUtil.verify(token, username, user.getUserPassword(), user.getCpId())) {
            throw new AuthenticationException("用户名或密码错误");
        }
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
