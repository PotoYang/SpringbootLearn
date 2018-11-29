package com.potoyang.learn.securityjwt.entity;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018-11-27 13:56:40
 * Modified By:
 * Description:
 */
public class Configuration implements Serializable {
    private static final long serialVersionUID = 1L;
    // E:/workspace/intellij_workspace/SpringbootLearn/security-jwt
    // E:/workspace/intellij_workspace/SpringbootLearn
    // ./security-jwt/src/main/java/com/potoyang/learn/securityjwt

    //配置管理表
    private Integer id;
    //生命周期：0到期自动删除 1保存一个月 3三个月 6半年
    private Integer lifeCycle;
    //发布次数：0即时发布，1一次，2两次...
    private Integer releaseTime;
    //系统中所有视频的总大小:G
    private Float videoTotal;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(Integer lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public Integer getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Integer releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Float getVideoTotal() {
        return videoTotal;
    }

    public void setVideoTotal(Float videoTotal) {
        this.videoTotal = videoTotal;
    }

}