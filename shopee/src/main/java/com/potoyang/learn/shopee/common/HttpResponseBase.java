package com.potoyang.learn.shopee.common;

import lombok.Data;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 00:20
 * Modified:
 * Description: T 对应 data 的数据类型
 */
@Data
public class HttpResponseBase {
    // 请求版本信息
    private String version;
    // 错误码
    private Integer error;
    // 错误信息
    private String error_msg;
}
