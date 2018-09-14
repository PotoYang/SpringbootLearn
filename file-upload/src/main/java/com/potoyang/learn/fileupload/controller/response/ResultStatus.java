package com.potoyang.learn.fileupload.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author potoyang
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatus {
    /**
     * 1 开头为判断文件在系统的状态
     */
    EXISTED(100, "该文件已存在"),

    NONE(101, "该文件没有上传过"),

    ING(102, "该文件上传了一部分"),

    SUCCESS(200, "成功"),

    FAILED(-1, "失败s");

    private final int value;

    private final String reasonPhrase;

    ResultStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
