package com.netty.netty_rpc.handler;

import com.alibaba.fastjson.JSONObject;
import com.netty.netty_rpc.client.DefaultFuture;
import com.netty.netty_rpc.client.Response;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		if("ping".equals(msg)) {
			ctx.channel().writeAndFlush("ping\r\n");
			
			return;
		}
		
		ctx.channel().attr(AttributeKey.valueOf("sss")).set(msg);
		
		Response response = JSONObject.parseObject(msg.toString(),Response.class);
		
		DefaultFuture.receive(response);
		
		ctx.channel().close();
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		
		//super.userEventTriggered(ctx, evt);
	}

	

	
 
}
