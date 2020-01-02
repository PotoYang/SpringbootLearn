package com.potoyang.learn.shirojwt.entity;


/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-09-10 11:22:12
 * Modified By:
 * Description:
 */
public class SysRole {
    
    private Integer id;
    
    private String roleName;
    
    private String memo;


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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}