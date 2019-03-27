package com.potoyang.learn.blog.global;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 18:05
 * Modified:
 * Description:
 */
@Data
public class RestResult<T> {
    private int code = 0;
    private String msg = "success";
    private T data;

    public RestResult() {
    }

    public RestResult(T data) {
        this.data = data;
    }

    public RestResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
