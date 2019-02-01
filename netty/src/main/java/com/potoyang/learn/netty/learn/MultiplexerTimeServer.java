package com.potoyang.learn.netty.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/2/1 13:58
 * Modified:
 * Description:
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;
    private volatile boolean stop;

    MultiplexerTimeServer(int port) {
        // 对资源进行初始化
        try {
            // 创建多路复用器 Selector
            selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            // 设置为非阻塞
            serverChannel.configureBlocking(false);
            // 绑定端口，设置 backlog
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);
            // 将 ServerSocketChannel 注册到 Selector
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                // selector 每隔 1s 被唤醒一次
                selector.select(1000);
                // 遍历 selector
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        // 根据 SelectionKey 的操作位进行判断即可获知网络事件的类型
        if (key.isValid()) {
            // 处理接入请求的消息
            if (key.isAcceptable()) {
                // 接收新的请求
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                // Add the new connection to the selector
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                // Read the data
                SocketChannel sc = (SocketChannel) key.channel();
                // 开辟 1M 缓冲区
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                // 此处 read 为非阻塞
                int readBytes = sc.read(readBuffer);
                // 读取到了字节，对字节进行编解码
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ? LocalDateTime.now().toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else if (readBytes < 0) {
                    // 链路已经关闭，需要关闭 SocketChannel，释放资源
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
