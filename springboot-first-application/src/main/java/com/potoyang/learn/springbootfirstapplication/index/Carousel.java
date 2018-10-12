package com.potoyang.learn.springbootfirstapplication.index;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 17:08
 * Modified By:
 * Description:
 */
public class Carousel implements Serializable {
    private static final long serialVersionUID = 1428776138139790253L;

    private String path;

    public Carousel() {
    }

    public Carousel(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
