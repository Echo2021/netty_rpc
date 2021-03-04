package com.netty.netty_rpc.server;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

import com.netty.netty_rpc.contants.Contants;
import com.netty.netty_rpc.factory.ZookeeperFactory;
import com.netty.netty_rpc.handler.SimpleServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class NettyServer {

	//private static final String Contants = null;

	public static void main(String[] args) throws Exception {

		ServerBootstrap bootstrap = new ServerBootstrap();

		EventLoopGroup parentGroup = new NioEventLoopGroup();

		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {

			bootstrap.group(parentGroup, childGroup);

			bootstrap.option(ChannelOption.SO_BACKLOG, 128).childOption(ChannelOption.SO_KEEPALIVE, false)
					.channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {

							ch.pipeline().addLast(new DelimiterBasedFrameDecoder(65535,Delimiters.lineDelimiter()[0]));

							ch.pipeline().addLast(new StringDecoder());
							
							ch.pipeline().addLast(new IdleStateHandler(30,20,10,TimeUnit.SECONDS));

							ch.pipeline().addLast(new SimpleServerHandler());

							ch.pipeline().addLast(new StringEncoder());
						}

					});

			ChannelFuture f = bootstrap.bind(8082).sync();

			//服务器注册到zookeeper
			CuratorFramework client = ZookeeperFactory.create();
			
			InetAddress netAddress = InetAddress.getLocalHost();
			
			client.create().withMode(CreateMode.EPHEMERAL).forPath(Contants.SERVER_PATH+netAddress.getHostAddress());
			
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			parentGroup.shutdownGracefully();

			childGroup.shutdownGracefully();
		}

	}
}
