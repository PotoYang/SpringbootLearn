package com.potoyang.learn.myfileupload.service;

import com.potoyang.learn.myfileupload.entity.YUploadParam;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/11 18:45
 * Modified:
 * Description:
 */
public interface YUploadService {
    Boolean uploadFile(YUploadParam param) throws IOException;
}
