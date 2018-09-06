package com.potoyang.learn.securityjwt.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 13:48
 * Modified By:
 * Description:
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {
    private static final String TAG = JWTLoginFilter.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTLoginFilter.class);

    JWTLoginFilter(String url, AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authenticationManager);
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        String loginName = request.getParameter("loginName");
        String userPassword = request.getParameter("userPassword");
        LOGGER.info(TAG + " loginName : " + loginName + " userPassword : " + userPassword);
        return getAuthenticationManager()
                .authenticate(new UsernamePasswordAuthenticationToken(loginName, userPassword));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) {
        TokenAuthenticationService.addAuthentication(response, authResult.getName());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        LOGGER.info(TAG + " unsuccessfulAuthentication");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println("{\"data\":\"password or account is error\"}");
    }
}
