package com.potoyang.learn.springbootfirstapplication;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/27 13:41
 * Modified By:
 * Description:
 */
public class RestResult<T> {

    public static final int SUCCESS = 0;
    public static final int FAIL = -1;


    private String msg = "success";
    private Integer code = SUCCESS;
    private T data;

    public RestResult(T data) {
        this.data = data;
    }

    public RestResult(String msg, Integer code, T data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
