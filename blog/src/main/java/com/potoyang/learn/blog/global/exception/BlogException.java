package com.potoyang.learn.blog.global.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 19:55
 * Modified:
 * Description:
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BlogException extends Exception {

    private String errorMsg;

    public BlogException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

}


