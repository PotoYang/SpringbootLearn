package com.potoyang.learn.mybatisredis;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/20 11:50
 * Modified:
 * Description:
 */
@Data
public class Test implements Serializable {
    private static final long serialVersionUID = -8743721599914996922L;

    private Integer id;
    private String name;
}
