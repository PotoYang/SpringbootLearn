package com.potoyang.learn.securityjwt.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/3 17:12
 * Modified By:
 * Description:
 */
@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = -8941402536214327481L;

    private Integer id;
    private String userName;
    private String loginName;
    private String userPassword;
    private String email;
    private Boolean enabled;
    private String cpId;
    private String createTime;
    private String modifyTime;
}
