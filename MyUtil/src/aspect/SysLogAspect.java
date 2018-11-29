import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/17 09:23
 * Modified By:
 * Description:
 */
@Aspect
@Component
public class SysLogAspect {

    private final SysManageService sysManageService;
    private final ContentProviderService contentProviderService;

    @Autowired
    public SysLogAspect(SysManageService sysManageService, ContentProviderService contentProviderService) {
        this.sysManageService = sysManageService;
        this.contentProviderService = contentProviderService;
    }

    // 定义切点，在注解的位置切入代码
    @Pointcut("@annotation(cn.ipanel.deliverycenter.aspect.MyLog)")
    public void sysLogPointCut() {

    }

    // 切面，配置通知
    @AfterReturning("sysLogPointCut()")
    private void saveSysLog(JoinPoint joinPoint) {
        SysLog sysLog = new SysLog();

        // 从切面织入点处通过反射机制获取织入点处的方法
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取切入点所在的方法
        Method method = signature.getMethod();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginName = (String) request.getAttribute(Constant.LOGIN_NAME);
        Integer providerId = (Integer) request.getAttribute(Constant.PROVIDER_ID);

        sysLog.setUserName(loginName);
        sysLog.setProviderId(providerId);
        sysLog.setProviderName(contentProviderService.getProviderNameByProviderId(providerId));

        MyLog myLog = method.getAnnotation(MyLog.class);
        if (!StringUtils.isEmpty(myLog)) {
            sysLog.setOperation(myLog.value());
        }
        sysLog.setIpAddress(request.getRemoteAddr());
        // 保存敏感操作记录
        sysManageService.save(sysLog);
    }
}

