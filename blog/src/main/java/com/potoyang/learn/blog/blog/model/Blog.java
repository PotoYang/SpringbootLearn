package com.potoyang.learn.blog.blog.model;

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
 * @since 2019/3/27 17:53
 * Modified:
 * Description:
 */
@Data
@TableName("tb_blog")
public class Blog implements Serializable {
    private static final long serialVersionUID = -4487896138010237208L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    // blog 标题
    @TableField(value = "title")
    private String title;
    // blog 内容
    @TableField(value = "content")
    private String content;
    // blog 是否为草稿
    @TableField(value = "temp")
    private Boolean temp;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "modify_time")
    private LocalDateTime modifyTime;
}
