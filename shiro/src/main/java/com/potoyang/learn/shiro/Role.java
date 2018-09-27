package com.potoyang.learn.shiro;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/9/19 14:59
 * Modified By:
 * Description:
 */
public class Role implements Serializable {
    private static final long serialVersionUID = 1072151567504455319L;

    private Integer id;
    private String roleName;
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
