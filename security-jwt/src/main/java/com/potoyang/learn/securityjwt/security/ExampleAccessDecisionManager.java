package com.potoyang.learn.securityjwt.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 19:57
 * Modified By:
 * Description:
 */
@Service
public class ExampleAccessDecisionManager implements AccessDecisionManager {

    private static final String TAG = ExampleAccessDecisionManager.class.getSimpleName();
    private static final Logger LOG = LoggerFactory.getLogger(ExampleAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (collection == null) {
            return;
        }

        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            LOG.info(TAG + " needRole " + needRole);
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needRole.equals(grantedAuthority.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right!");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
