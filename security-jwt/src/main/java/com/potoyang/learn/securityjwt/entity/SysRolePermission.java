package com.potoyang.learn.securityjwt.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 17:49
 * Modified By:
 * Description:
 */
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 921809984887911882L;
    private Integer roleId;
    private Integer permissionId;

}
