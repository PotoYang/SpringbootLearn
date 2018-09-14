package com.potoyang.learn.securityjwt.token;

import com.potoyang.learn.securityjwt.entity.SysUser;
import com.potoyang.learn.securityjwt.service.SysUserService;
import com.potoyang.learn.securityjwt.util.Encrypt;
import com.potoyang.learn.securityjwt.util.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 16:06
 * Modified By:
 * Description:
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final String TAG = CustomAuthenticationProvider.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        LOGGER.info(TAG + " enter authenticate");
        // 获取认证的帐号 & 密码
        String loginName = authentication.getName();
        String userPassword = authentication.getCredentials().toString();

        userPassword = Encrypt.e(userPassword);
        LOGGER.info(TAG + " loginName : " + loginName + " userPassword : " + userPassword);
        SysUser users = null;
        try {
            SysUserService sysUserService = (SysUserService) SpringUtil
                    .getBean("sysUserService");
            users = sysUserService.getSysUser(loginName, userPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 认证逻辑
        if (users != null) {
            // 这里设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//			authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
//			authorities.add(new GrantedAuthorityImpl("AUTH_WRITE"));
            // 生成令牌
            return new UsernamePasswordAuthenticationToken(loginName, userPassword, authorities);
        } else {
            throw new BadCredentialsException("密码错误");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     *
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
