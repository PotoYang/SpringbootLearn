package com.potoyang.learn.fileupload.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/20 09:30
 * Modified By:
 * Description:
 */
@Data
public class FileCheckEntity implements Serializable {
    private static final long serialVersionUID = 8967935390256466580L;

    private String fileName;
    private Integer isFileExist;

    public FileCheckEntity() {
    }

    public FileCheckEntity(String fileName, Integer isFileExist) {
        this.fileName = fileName;
        this.isFileExist = isFileExist;
    }
}
