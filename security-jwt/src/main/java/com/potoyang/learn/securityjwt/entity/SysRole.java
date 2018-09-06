package com.potoyang.learn.securityjwt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 17:48
 * Modified By:
 * Description:
 */
@Data
public class SysRole implements Serializable {
    private static final long serialVersionUID = 859329703830391433L;

    private Integer id;
    private String roleName;
    private String memo;

}
