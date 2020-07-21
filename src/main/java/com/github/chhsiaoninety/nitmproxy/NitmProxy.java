package com.github.chhsiaoninety.nitmproxy;

import com.github.chhsiaoninety.nitmproxy.enums.ProxyMode;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class NitmProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(NitmProxy.class);

    private NitmProxyConfig config;

    public NitmProxy(NitmProxyConfig config) {
        this.config = config;
    }

    public void start() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new NitmProxyInitializer(config));
            Channel channel = bootstrap
                    .bind(config.getHost(), config.getPort())
                    .sync()
                    .channel();

            System.err.format("nitmproxy is listened at http://%s:%d%n",
                              config.getHost(), config.getPort());

            channel.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}