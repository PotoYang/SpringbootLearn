package com.potoyang.learn.fileupload.controller.response;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/8 13:02
 * Modified:
 * Description:
 */
@Data
public class RestResult<T> {
    public static final int SUCCESS = 0;

    private String msg = "success";
    private int code;
    private T data;

    public RestResult(T data) {
        this.code = SUCCESS;
        this.data = data;
    }

    public RestResult(String msg, int code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }
}
