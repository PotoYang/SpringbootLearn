package com.potoyang.learn.netty;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:04
 * Modified:
 * Description:
 */
public class RequestData {
    private int intValue;
    private String stringValue;

    public int getIntValue() {
        return intValue;
    }

    public void setIntValue(int intValue) {
        this.intValue = intValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "intValue=" + intValue +
                ", stringValue='" + stringValue + '\'' +
                '}';
    }
}
