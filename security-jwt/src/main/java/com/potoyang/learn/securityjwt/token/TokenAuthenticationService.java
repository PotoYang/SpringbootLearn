package com.potoyang.learn.securityjwt.token;

import com.potoyang.learn.securityjwt.entity.SysUser;
import com.potoyang.learn.securityjwt.entity.SysUserRole;
import com.potoyang.learn.securityjwt.service.SysUserService;
import com.potoyang.learn.securityjwt.util.SpringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 13:59
 * Modified By:
 * Description:
 */
public class TokenAuthenticationService {
    private static final String TAG = TokenAuthenticationService.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenAuthenticationService.class);

    private static final long EXPIRATION_TIME = 432000000;
    private static final String SECRET = "123@delivery";
    private static final String HEADER_STRING = "Authorization";

    public static void addAuthentication(HttpServletResponse response, String loginName) {
        try {
            LOGGER.info(TAG + " enter addAuthentication");
            // 生成JWT
            String token = Jwts.builder()
                    // 保存权限（角色）
//                    .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                    // 登陆帐
                    // 号写入标题
                    .setSubject(loginName)
                    // 有效期设置
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    // 签名设置
                    .signWith(SignatureAlgorithm.HS256, SECRET).compact();
            response.addHeader(HEADER_STRING, token);
            response.setContentType("application/json");
            response.getOutputStream().println("{\"msg\":\"" + token + "\"}");
        } catch (Exception e) {
            LOGGER.error(TAG + " addAuthentication throw e : " + e);
        }
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        LOGGER.info(TAG + " enter getAuthentication, url : " + url);
        String token = request.getHeader(HEADER_STRING);
        LOGGER.info(TAG + " token == null " + (token == null));
        if (!StringUtils.isEmpty(token)) {
            try {
                // 解析 Token
                Claims claims = Jwts.parser()
                        // 验签
                        .setSigningKey(SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token).getBody();
                // 拿用户名
                String loginName = claims.getSubject();
                SysUserService sysUserService = (SysUserService) SpringUtil.getBean("sysUserService");
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                List<SysUserRole> sysUserRoleList = sysUserService.getRoleByLoginName(loginName);
                for (SysUserRole sysUserRole : sysUserRoleList) {
                    SysUser sysUser = sysUserService.getSysUserById(sysUserRole.getUserId());
                    if (sysUser != null) {
                        String name = sysUser.getLoginName();
                        LOGGER.info(TAG + " loginName : " + name);
                        request.setAttribute("login_user_name", name);
                    }
                    if (sysUserRole.getRoleId() != null) {
                        SimpleGrantedAuthority simpleGrantedAuthority =
                                new SimpleGrantedAuthority(sysUserService.getRoleNameById(sysUserRole.getRoleId()));
                        grantedAuthorities.add(simpleGrantedAuthority);
                    }
                }
                return loginName != null ? new UsernamePasswordAuthenticationToken(
                        loginName, null, grantedAuthorities) : null;
            } catch (Exception e) {
                LOGGER.error("token error");
                return null;
            }
        }
        return null;
    }
}
