/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/19 22:16
 */

package com.parallelline1996.grpc.demo.consumer.client;

import grpc.springboot.demo.api.HelloWorldGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GrpcClient {

    private static final Logger logger = LoggerFactory.getLogger(GrpcClient.class);

    // Grpc 服务端的地址
    @Value("${grpc.server.host}")
    private String host;

    // Grpc 服务端暴露的接口
    @Value("${grpc.server.port}")
    private int port;

    private HelloWorldGrpc.HelloWorldBlockingStub stub;
    private HelloWorldGrpc.HelloWorldFutureStub futureStub;

    public void start() {
        // 开启channel
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();

        // 通过 channel 获取服务端 blocking-stub
        stub = HelloWorldGrpc.newBlockingStub(channel);
        // 通过 channel 获取服务端 future-stub
        futureStub = HelloWorldGrpc.newFutureStub(channel);

        logger.info("gRPC client start, server address: host:{}, port:{}", host, port);
    }

    public HelloWorldGrpc.HelloWorldBlockingStub getStub() {
        return this.stub;
    }

    public HelloWorldGrpc.HelloWorldFutureStub getFutureStub() {
        return this.futureStub;
    }
}

