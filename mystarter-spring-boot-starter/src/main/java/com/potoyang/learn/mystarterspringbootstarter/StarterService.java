package com.potoyang.learn.mystarterspringbootstarter;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/15 16:28
 * Modified By:
 * Description:
 */
public class StarterService {
    private String config;

    public StarterService(String config) {
        this.config = config;
    }

    public String[] split(String separatorChar) {
        return this.config.split(separatorChar);
    }
}
