package com.potoyang.learn.blog.global.exception;

import com.potoyang.learn.blog.global.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 19:57
 * Modified:
 * Description:
 */
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BlogException.class)
    public RestResult<String> exceptionBlogHandler(Exception ex) {
        RestResult<String> errorResult = new RestResult<>();
        errorResult.setCode(HttpStatus.BAD_REQUEST.value());
        errorResult.setMsg(ex.getMessage());
        return errorResult;
    }
}
