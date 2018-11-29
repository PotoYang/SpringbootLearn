import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/11/27 15:34
 * Modified:
 * Description:
 */
public class Log {
    private static Logger logger;
    private static final Object OBJECT = new Object();

    static {
        init();
    }

    private static void init() {
        synchronized (OBJECT) {
            try {
                logger = LoggerFactory.getLogger("MyLog");
            } catch (Exception e) {
                logger = null;
                System.out.println("Log init failure!");
            }
        }
    }

    public static void i(String tag, Object msg) {
        if (logger == null) {
            System.out.println("[INFO] [" + tag + "]: " + msg);
        } else {
            logger.info("[INFO] [" + tag + "] : {}", msg);
        }
    }

    public static void w(String tag, Object msg) {
        if (logger == null) {
            System.out.println("[WARN] [" + tag + "]: " + msg);
        } else {
            logger.warn("[WARN] [" + tag + "] : {}", msg);
        }
    }

    public static void d(String tag, Object msg) {
        if (logger == null) {
            System.out.println("[DEBUG] [" + tag + "]: " + msg);
        } else {
            logger.debug("[DEBUG] [" + tag + "] : {}", msg);
        }
    }

    public static void e(String tag, Object msg) {
        if (logger == null) {
            System.out.println("[ERROR] [" + tag + "]: " + msg);
        } else {
            logger.error("[ERROR] [" + tag + "] : {}", msg);
        }
    }
}
