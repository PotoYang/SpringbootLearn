package com.potoyang.learn.fileupload.controller.response;

import lombok.Data;

/**
 * @author potoyang
 */
@Data
public class ResultVO<T> {

    private ResultStatus status;
    private String msg;
    private T data;

    public ResultVO(ResultStatus status) {
        this(status, status.getReasonPhrase(), null);
    }

    public ResultVO(ResultStatus status, T data) {
        this(status, status.getReasonPhrase(), data);
    }

    public ResultVO(ResultStatus status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultVO{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
