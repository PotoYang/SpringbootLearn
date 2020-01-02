package com.potoyang.learn.shirojwt.controller;

import com.potoyang.learn.shirojwt.controller.response.RestResult;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/10 14:46
 * Modified By:
 * Description:
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     * 捕捉shiro的异常
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public RestResult<?> handle401() {
        RestResult<?> result = new RestResult<>();
        result.setCode(RestResult.UNAUTHORIZED);
        result.setMsg("访问未授权");
        return result;
    }

    /**
     * 捕捉UnauthorizedException
     *
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public RestResult<?> handle401(UnauthorizedException ex) {
        RestResult<?> result = new RestResult<>();
        result.setCode(RestResult.UNAUTHORIZED);
        result.setMsg(ex.getMessage());
        return result;
    }

    /**
     * 捕捉其他所有异常
     *
     * @param request
     * @param ex
     * @return
     */
/*    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult<?> globalException(HttpServletRequest request, Throwable ex) {
        RestResult<?> result = new RestResult<>();
        result.setCode(getStatus(request).value());
        result.setMsg(ex.getMessage());
        return result;
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }*/
}
