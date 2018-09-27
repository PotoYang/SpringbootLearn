package com.potoyang.learn.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 18:42
 * Modified By:
 * Description:
 */
@Service
public class MySessionService {
    @Autowired
    private RedisSessionDAO redisSessionDAO;

    public String getUsername(String sessionId) {
        Session session = redisSessionDAO.readSession(sessionId);
        SimplePrincipalCollection collection = (SimplePrincipalCollection)
                session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        return (String) collection.getPrimaryPrincipal();
    }

}
