package com.potoyang.learn.netty.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/31 15:36
 * Modified:
 * Description:
 */
public class SocketServerTest {

    public static void main(String[] args) throws IOException {
        int port = 9000;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            Socket socket = null;
            while (true) {
                socket = server.accept();
                new Thread().start();
            }
        } finally {
            if (server != null) {
                server.close();
                server = null;
            }
        }
    }
}
