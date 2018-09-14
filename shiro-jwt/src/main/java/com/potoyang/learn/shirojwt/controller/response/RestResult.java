package com.potoyang.learn.shirojwt.controller.response;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/10 14:32
 * Modified By:
 * Description:
 */
@Data
public class RestResult<T> {

    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int CHECK_FAIL = 1;
    public static final int NO_PERMISSION = 2;
    public static final int UNAUTHORIZED = 3;
    public static final int UNKNOWN_EXCEPTION = -99;

    private String msg = "success";
    private int code = SUCCESS;
    private T data;

    public RestResult() {
        super();
    }

    public RestResult(T data) {
        super();
        this.data = data;
    }

    public RestResult(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }

}
