/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/18 20:19
 */

package com.parallelline1996.grpc.demo.server.service;

import grpc.springboot.demo.api.HelloWorldGrpc;
import grpc.springboot.demo.api.HelloWorldService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class HelloServiceImpl extends HelloWorldGrpc.HelloWorldImplBase {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello(HelloWorldService.HelloRequest request,
                         StreamObserver<HelloWorldService.HelloResponse> responseObserver) {
        String message = request.getMessage();

        logger.info("Client Received message:{}", message);
        if (Objects.equals("error", message)) {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid order ID received.").asException());
            return;
        }

        // 创建结果，返回响应内容
        String rsp = String.format("Hello, %s. This message have send to server", message);
        HelloWorldService.HelloResponse response = HelloWorldService.HelloResponse
                .newBuilder()
                .setMessage(rsp)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

