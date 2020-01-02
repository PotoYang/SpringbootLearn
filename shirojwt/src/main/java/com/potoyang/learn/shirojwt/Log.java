package com.potoyang.learn.shirojwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/9/10 13:46
 * Modified By:
 * Description:
 */
public class Log {
    private static final Logger LOGGER = LoggerFactory.getLogger(Log.class);

    public static void i(String tag, String msg) {
        LOGGER.info(tag + " " + msg);
    }

    public static void e(String tag, String msg) {
        LOGGER.info(tag + " " + msg);
    }
}
