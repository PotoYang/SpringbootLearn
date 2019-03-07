package com.potoyang.learn.unittestsample.model;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:22
 * Modified:
 * Description:
 */
@Data
public class Response {
    private int value;
    private String msg;

    public Response() {
        super();
    }

    public Response(int value, String msg) {
        super();
        this.value = value;
        this.msg = msg;
    }
}
