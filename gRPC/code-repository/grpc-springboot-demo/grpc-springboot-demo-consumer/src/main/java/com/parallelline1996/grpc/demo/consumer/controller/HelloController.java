/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/19 22:12
 */

package com.parallelline1996.grpc.demo.consumer.controller;

import com.google.common.util.concurrent.ListenableFuture;
import com.parallelline1996.grpc.demo.consumer.client.GrpcClient;
import grpc.springboot.demo.api.HelloWorldGrpc;
import grpc.springboot.demo.api.HelloWorldService;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    GrpcClient client;

    /**
     * 同步发送，阻塞
     */
    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", defaultValue = "test", required = false) String name) {
        logger.info("Server received request.name={}", name);
        // 构建一个请求
        HelloWorldService.HelloRequest request = HelloWorldService.HelloRequest
                .newBuilder()
                .setMessage(name)
                .build();

        // 使用stub发送请求至服务端
        try {
//            HelloWorldGrpc.HelloWorldBlockingStub stub = client.getStub().withDeadline(Deadline.after(2L, TimeUnit.SECONDS));
            HelloWorldGrpc.HelloWorldBlockingStub stub = client.getStub();
            HelloWorldService.HelloResponse response = stub.sayHello(request);
            logger.info("Server response received: [{}]", response.toString());
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            logger.info(" Error Received - Error code : {}", e.getStatus().getCode());
        }
        return "error";
    }

    /**
     * 异步发送，但直接 get() 获取结果，阻塞
     */
    @GetMapping("/hello-async")
    public String helloAsync(@RequestParam(name = "name", defaultValue = "test", required = false) String name) {
        logger.info("Server received request.name={}", name);
        // 构建一个请求
        HelloWorldService.HelloRequest request = HelloWorldService.HelloRequest
                .newBuilder()
                .setMessage(name)
                .build();

        // 使用stub发送请求至服务端
        try {
            ListenableFuture<HelloWorldService.HelloResponse> listenableFuture = client.getFutureStub().sayHello(request);
            HelloWorldService.HelloResponse response = listenableFuture.get();
            logger.info("Server response received: [{}]", response.toString());
            return response.getMessage();
        } catch (StatusRuntimeException e) {
            logger.info(" Error Received - Error code : {}", e.getStatus().getCode());
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return "error";
    }

    /**
     * 异步发送，通过线程池异步处理回调
     */
    @GetMapping("/hello-async1")
    public String helloAsync1(@RequestParam(name = "name", defaultValue = "test", required = false) String name) {
        logger.info("Server received request.name={}", name);
        // 构建一个请求
        HelloWorldService.HelloRequest request = HelloWorldService.HelloRequest
                .newBuilder()
                .setMessage(name)
                .build();

        // 使用stub发送请求至服务端
        try {
//            HelloWorldGrpc.HelloWorldFutureStub futureStub =
//                    client.getFutureStub().withDeadlineAfter(1L, TimeUnit.SECONDS);
            HelloWorldGrpc.HelloWorldFutureStub futureStub = client.getFutureStub();
            ListenableFuture<HelloWorldService.HelloResponse> listenableFuture = futureStub.sayHello(request);

            listenableFuture.addListener(() -> {
                try {
                    HelloWorldService.HelloResponse response = listenableFuture.get();
                    logger.info("Server response received: [{}]", response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, new ThreadPoolExecutor(1, 1, 60,
                    TimeUnit.MILLISECONDS, new SynchronousQueue<>()));

            return "async";
        } catch (StatusRuntimeException e) {
            logger.info(" Error Received - Error code : {}", e.getStatus().getCode());
        } catch (Exception e) {
            logger.error("error ", e);
        }
        return "error";
    }
}

