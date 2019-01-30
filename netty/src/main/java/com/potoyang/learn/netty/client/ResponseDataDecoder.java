package com.potoyang.learn.netty.client;

import com.potoyang.learn.netty.ResponseData;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/1/24 16:07
 * Modified:
 * Description:
 */
public class ResponseDataDecoder
        extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {

        ResponseData data = new ResponseData();
        data.setIntValue(in.readInt());
        out.add(data);
    }
}
