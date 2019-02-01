package com.potoyang.learn.netty.learn;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/2/1 13:47
 * Modified:
 * Description:
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 9000;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException ignored) {
            }
        }

        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);

        new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
    }
}
