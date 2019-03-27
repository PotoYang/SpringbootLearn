package com.potoyang.learn.fileupload.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/27 14:17
 * Modified:
 * Description:
 */
@Data
public class Blog implements Serializable {

    private String title;
    private String content;
    private boolean temp;
}
