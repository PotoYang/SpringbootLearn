package com.potoyang.learn.netty.server;

import com.potoyang.learn.netty.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:34
 * Modified:
 * Description:
 */
public class ResponseDataEncoder
        extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          ResponseData msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getIntValue());
    }
}
