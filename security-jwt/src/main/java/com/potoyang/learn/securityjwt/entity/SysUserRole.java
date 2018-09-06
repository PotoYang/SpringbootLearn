package com.potoyang.learn.securityjwt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 17:40
 * Modified By:
 * Description:
 */
@Data
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = 1034501281764785830L;

    private Integer userId;
    private Integer roleId;
}
