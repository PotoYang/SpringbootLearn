package com.potoyang.learn.netty;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:08
 * Modified:
 * Description:
 */
public class ResponseData {
    private int intValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "intValue=" + intValue +
                '}';
    }
}
