package com.potoyang.learn.fileupload.config;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/7/12 14:59
 * Modified By:
 * Description:
 */
public class Constants {
    /**
     * 缓存键值
     */
    public static final Map<Class<?>, String> cacheKeyMap = new HashMap<>();
    /**
     * 保存文件所在路径的key，eg.FILE_MD5:1243jkalsjflkwaejklgjawe
     */
    public static final String FILE_MD5_KEY = "FILE_MD5:";
    /**
     * 保存上传文件的状态
     */
    public static final String FILE_UPLOAD_STATUS = "FILE_UPLOAD_STATUS";

    /**
     * 需要在Excel中提取的列数据
     */
    public static final Map<String, Integer> TITLE_FIELDS = new HashMap<String, Integer>() {
        private static final long serialVersionUID = -906222466953119489L;

        {
            put("节目集ID", 1);
            put("节目集名称", 2);
            put("节目ID", 3);
            put("节目名称", 4);
            put("集/期数", 5);
            put("视频地址", 6);

        }
    };

}
