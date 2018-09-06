package com.potoyang.learn.securityjwt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:05
 * Modified By:
 * Description:
 */
@Service
public class ExampleFilterSecurityInterceptor
        extends AbstractSecurityInterceptor implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleFilterSecurityInterceptor.class);
    private static final String TAG = ExampleFilterSecurityInterceptor.class.getSimpleName();

    @Autowired
    private InvocationSecurityMetadataSource invocationSecurityMetadataSource;
    @Autowired
    private ExampleAccessDecisionManager exampleAccessDecisionManager;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostConstruct
    private void init() {
        super.setAuthenticationManager(authenticationManager);
        super.setAccessDecisionManager(exampleAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        LOGGER.info(TAG + " enter doFilter");
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        String url = fi.getRequestUrl();
        LOGGER.info(TAG + " url:" + url);
        ((HttpServletResponse) response).setHeader("Access-Control-Allow-Origin", "*");
        invoke(fi);
    }

    private void invoke(FilterInvocation fi) throws IOException, ServletException {
        // 在执行doFilter之前，进行权限的检查
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.invocationSecurityMetadataSource;
    }
}
