package com.potoyang.learn.securityjwt.security;

import com.potoyang.learn.securityjwt.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 20:08
 * Modified By:
 * Description:
 */
@Service
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(InvocationSecurityMetadataSource.class);
    private static final String TAG = InvocationSecurityMetadataSource.class.getSimpleName();

    @Autowired
    private SysUserService sysUserService;
    public static Map<String, Collection<ConfigAttribute>> resourceMap = null;


    public InvocationSecurityMetadataSource() {
        try {
            loadResourceDefine();
        } catch (Exception e) {
            LOGGER.error(TAG + " init InvocationSecurityMetadataSource throw exception : " + e);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        LOGGER.info(TAG + " enter getAttributes");
        FilterInvocation filterInvocation = (FilterInvocation) o;
        if (resourceMap == null) {
            try {
                loadResourceDefine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String resURL : resourceMap.keySet()) {
            RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);
            if (requestMatcher.matches(filterInvocation.getRequest())) {
                Collection<ConfigAttribute> attList = resourceMap.get(resURL);
                LOGGER.info(TAG + " resURL : " + resURL + " attList.size : " + attList.size());
                if (attList.size() == 0) {
                    throw new IllegalArgumentException("no right");
                }
                return attList;
            }
        }
        LOGGER.info(TAG + " is null");
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @PostConstruct
    private void loadResourceDefine() {
        if (sysUserService == null) {
            return;
        }
        List<String> urls = sysUserService.getAllUrl();
        LOGGER.info(TAG + " urls == null : " + (urls == null));
        resourceMap = new HashMap<>();

        if (urls != null) {
            for (String url : urls) {
                Collection<ConfigAttribute> atts = new ArrayList<>();
                // 查询出url对应的权限
                List<String> roleNames = sysUserService.getRoleNameByUrl(url);
                for (String roleName : roleNames) {
                    ConfigAttribute ca = new SecurityConfig(roleName);
                    atts.add(ca);
                }
                resourceMap.put(url, atts);
            }
        }

        // 打印查看权限与资源的对应关系
        LOGGER.info("资源（URL）数量：" + resourceMap.size());
        Set<String> keys = resourceMap.keySet();
        for (String key : keys) {
            LOGGER.info("url:" + key + " role:" + resourceMap.get(key));
        }
    }

    public static void reLoadResource() {
        try {
            new InvocationSecurityMetadataSource().loadResourceDefine();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
