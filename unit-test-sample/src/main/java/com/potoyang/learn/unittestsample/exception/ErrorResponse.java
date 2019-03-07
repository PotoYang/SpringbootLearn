package com.potoyang.learn.unittestsample.exception;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:36
 * Modified:
 * Description:
 */
@Data
public class ErrorResponse {
    private int errorCode;
    private String message;
}
