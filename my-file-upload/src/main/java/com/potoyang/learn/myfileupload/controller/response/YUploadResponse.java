package com.potoyang.learn.myfileupload.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 18:43
 * Modified:
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YUploadResponse implements Serializable {
    private static final long serialVersionUID = -8292447639153259323L;

    private Boolean success;
}
