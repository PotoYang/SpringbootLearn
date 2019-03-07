package com.potoyang.learn.unittestsample.exception;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:18
 * Modified:
 * Description:
 */
@Data
public class ToDoException extends Exception {
    private static final long serialVersionUID = 8314711374151528757L;

    private String errorMessage;

    public ToDoException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public ToDoException() {
        super();
    }
}
