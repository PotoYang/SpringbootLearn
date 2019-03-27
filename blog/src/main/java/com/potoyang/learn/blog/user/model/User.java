package com.potoyang.learn.blog.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/20 19:52
 * Modified:
 * Description:
 */
@Data
@TableName("tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 3902608599216308135L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // 用户名
    @TableField("user_name")
    private String userName;
    // 登录名
    @TableField("login_name")
    private String loginName;
    // 密码
    @TableField("user_password")
    private String userPassword;
    // 电子邮箱
    @TableField("email")
    private String email;
    // 是否启用
    @TableField("enable")
    private Boolean enable;
    // 内容商 id
    @TableField("provider_id")
    private Integer providerId;
    // 数据创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;
    // 数据修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("modify_time")
    private LocalDateTime modifyTime;
}
