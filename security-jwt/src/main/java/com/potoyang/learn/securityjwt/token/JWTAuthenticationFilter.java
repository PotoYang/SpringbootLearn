package com.potoyang.learn.securityjwt.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/4 15:10
 * Modified By:
 * Description:
 */
class JWTAuthenticationFilter extends GenericFilterBean {

    private static final String TAG = JWTAuthenticationFilter.class.getSimpleName();
    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURL().toString();
        LOGGER.info(TAG + " enter doFilter,url:" + url);
        Authentication authentication;
        try {
            authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
            LOGGER.info(TAG + " authentication == null:" + (authentication == null) + ",avoidAuth:" + false);
        } catch (Exception e) {
            e.printStackTrace();
            authentication = null;
        }
        LOGGER.info(TAG + " authentication == null:" + (authentication == null) + ", avoidAuth:" + false);
        // 认证未通过
        if (authentication == null) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.sendRedirect("/error");
//            httpServletResponse.getOutputStream().println("{\"data\":\"token is no available\"}");
        } else {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
}
