import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with Intellij IDEA.
 *
 * @author zhangyn
 * @Date 2018/11/1 17:01
 * Modified By:
 * Description:全局异常捕获
 * wangyb 增加对自定义异常的捕获
 */
@RestControllerAdvice
@Component
@Slf4j
public class GlobalExceptionHandler {

    private final SendMailService sendMailService;

    @Autowired
    public GlobalExceptionHandler(SendMailService sendMailService) {
        this.sendMailService = sendMailService;
    }

    @ExceptionHandler(value = BindException.class)
    public RestResult bindExceptionHandler(Exception e) {
        RestResult result = new RestResult<>();
        result.setCode(RestResult.CHECK_FAIL);
        result.setMsg(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public RestResult loginExceptionHandler(Exception e) {
        RestResult result = new RestResult<>();
        result.setCode(RestResult.NO_LOGIN);
        result.setMsg(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = AuthorizationException.class)
    public RestResult permExceptionHandler(Exception e) {
        RestResult result = new RestResult<>();
        result.setCode(RestResult.NO_PERMISSION);
        result.setMsg(e.getMessage());
        return result;
    }

    //对ftp异常进行捕获
    @ExceptionHandler(value = FTPException.class)
    public RestResult ftpExceptionHandler(Exception e) {
        RestResult result = new RestResult<>();
        result.setCode(RestResult.FTP_FAIL);
        result.setMsg(e.getMessage());
        return result;
    }

    //对参数异常进行捕获
    @ExceptionHandler(value = RequestParamErrorException.class)
    public RestResult paramExceptionHandler(Exception e) {
        RestResult result = new RestResult<>();
        result.setCode(RestResult.PARAM_FAIL);
        result.setMsg(e.getMessage());
        return result;
    }

    @ExceptionHandler(value = Exception.class)
    public RestResult exceptionHandler(Exception e) {
        log.error("by zhangyn: {}", e.getMessage(), e);
        RestResult result = new RestResult<>();
        result.setCode(RestResult.UNKNOWN_EXCEPTION);
        result.setMsg(e.getMessage());
        //未知异常发邮件
        log.info("未知异常发送成功！");
//        String subject = "内容商分发平台出现未知异常";
//        String text = e.toString();
//        String sender = emailUserName;
//        String receiver = ;
//        sendMailService.sendTextMail(subject, text, sender, receiver);
        return result;
    }

}
