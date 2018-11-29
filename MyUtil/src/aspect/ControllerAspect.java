import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerAspect {

    @Pointcut("execution(public cn.ipanel.deliverycenter.pojo.RestResult cn.ipanel.deliverycenter.controller.*.*(..))")
    public void restResult() {

    }

    @Around("restResult()")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        log.info("by zhangyn: {}", getMethodInfo(jp));
        return jp.proceed();
    }

    // 打印 controller 方法参数
    private String getMethodInfo(ProceedingJoinPoint jp) {
        String className = jp.getSignature().getDeclaringType().getSimpleName();
        String methodName = jp.getSignature().getName();
        String[] parameterNames = ((MethodSignature) jp.getSignature()).getParameterNames();
        StringBuilder sb = new StringBuilder().append(className).append(".").append(methodName).append("->");
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                // password 不记录到日志中
                if ("password".equals(parameterNames[i])) {
                    continue;
                }
                String value = jp.getArgs()[i] != null ? jp.getArgs()[i].toString() : "null";
                sb.append(parameterNames[i]).append(":").append(value).append("; ");
            }
        }
        return sb.toString();
    }

    // 异常处理
//	private RestResult<Object> exceptionHandler(ProceedingJoinPoint jp, Throwable e) {
//		RestResult<Object> result = new RestResult<Object>();
//		if (e instanceof AuthenticationException) {
//			result.setCode(RestResult.NO_LOGIN);
//			result.setMsg(e.getMessage());
//		} else {
//			log.error("by zhangyn: {}", jp.getSignature() + " error", e);
//			result.setCode(RestResult.UNKNOWN_EXCEPTION);
//			result.setMsg(e.toString());
//		}
//		return result;
//	}
}
