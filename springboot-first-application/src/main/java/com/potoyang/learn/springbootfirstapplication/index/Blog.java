package com.potoyang.learn.springbootfirstapplication.index;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/17 19:12
 * Modified By:
 * Description:
 */
public class Blog implements Serializable {
    private static final long serialVersionUID = 6249303970115393583L;

    private String render;
    private String html;

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
