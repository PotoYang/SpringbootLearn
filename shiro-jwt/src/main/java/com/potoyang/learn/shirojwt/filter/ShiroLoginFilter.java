package com.potoyang.learn.shirojwt.filter;

import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/5 19:03
 * Modified By:
 * Description:
 */
public class ShiroLoginFilter extends AccessControlFilter {
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        return false;
    }
}
