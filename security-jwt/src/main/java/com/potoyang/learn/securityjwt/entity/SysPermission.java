package com.potoyang.learn.securityjwt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 17:51
 * Modified By:
 * Description:
 */
@Data
public class SysPermission implements Serializable {
    private static final long serialVersionUID = -3509962792641459677L;

    private Integer id;
    private String permissionName;
    private String permissionUrl;
    private Integer parentId;
}
