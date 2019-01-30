package com.potoyang.learn.netty.client;

import com.potoyang.learn.netty.RequestData;
import com.potoyang.learn.netty.ResponseData;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:09
 * Modified:
 * Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception {

        RequestData msg = new RequestData();
        msg.setIntValue(123);
        msg.setStringValue(
                "all work and no play makes jack a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        System.out.println((ResponseData) msg);
        ctx.close();
    }
}
