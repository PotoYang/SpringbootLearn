package com.potoyang.learn.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:12
 * Modified:
 * Description:
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        int port = 9000;
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {

                @Override
                public void initChannel(SocketChannel ch)
                        throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(),
                            new ResponseDataDecoder(), new ClientHandler());
                }
            });

            b.handler(
                ch.pipeline().addLast(new RequestDataEncoder(),
                        new ResponseDataDecoder(), new ClientHandler());
            });

            ChannelFuture f = b.connect(host, port).sync();

            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
