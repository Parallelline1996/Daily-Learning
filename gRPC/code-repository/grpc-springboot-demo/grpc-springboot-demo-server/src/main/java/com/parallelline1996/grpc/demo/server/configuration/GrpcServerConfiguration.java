/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/18 20:19
 */


package com.parallelline1996.grpc.demo.server.configuration;

import com.parallelline1996.grpc.demo.server.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.*;

@Component
public class GrpcServerConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(GrpcServerConfiguration.class);

    // Grpc 服务端提供的服务
    @Autowired
    private HelloServiceImpl service;

    // Grpc 服务端的地址
    @Value("${grpc.server.port}")
    private int port;
    private Server server;
    private ThreadPoolExecutor executor;

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(10, 10, 60,
                TimeUnit.MILLISECONDS, new SynchronousQueue<>());
    }

    public void start() throws IOException {
        // 构建服务端
        logger.info("Starting gRPC on port {}.", port);

        // 使用默认线程池
        server = ServerBuilder.forPort(port).addService(service).build().start();

        // 使用自定义线程池用于处理请求
//        server = ServerBuilder.forPort(port).addService(service).executor(executor).build().start();

        logger.info("gRPC server started, listening on {}.", port);

        // 添加服务端关闭的逻辑
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Shutting down gRPC server.");
                GrpcServerConfiguration.this.stop();
                logger.info("gRPC server shut down successfully.");
            }
        }));
    }

    private void stop() {
        if (server != null) {
            // 关闭服务端
            server.shutdown();
        }
    }
}


