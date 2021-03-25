/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/25 23:11
 */

package com.parallelline1996.grpc.demo.consumer.controller;

import com.parallelline1996.grpc.demo.consumer.client.GrpcClient;
import grpc.springboot.demo.api.ComplexStructGrpc;
import grpc.springboot.demo.api.ComplexStructService;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ComplexStructController {

    private static final Logger logger = LoggerFactory.getLogger(ComplexStructController.class);

    private GrpcClient client;

    @Autowired
    public ComplexStructController(GrpcClient client) {
        this.client = client;
    }

    @GetMapping("/complex/struct/hello")
    public String hello() {
        List<String> allMessageArray = new ArrayList<>();
        allMessageArray.add("string1");
        allMessageArray.add("string2");
        allMessageArray.add("string3");

        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        ComplexStructService.ComplexStructRequest request = ComplexStructService.ComplexStructRequest
                .newBuilder()
                // 添加基础的数据类型
                .setMsg("msg")
                .setNum(12.5)
                .setLongNum(12L)
                .setShortNum(10)
                .setCheck(true)
                .addShortNumArray(100)
                .addShortNumArray(200)
                .addShortNumArray(300)
                // 添加字符串数组对象
                .addAllMessageArray(allMessageArray)
                // 添加 map 对象
                .putAllTestMap(testMap)
                // 在已添加的 map 对象基础上，再添加元素
                .putTestMap("key3", "value3")
                .setEnumTypeNum(ComplexStructService.enumType.ENUM_TYPE_3)
                .build();
        logger.info("Build request:{}", request);

        try {
            // 获取同步存根
            ComplexStructGrpc.ComplexStructBlockingStub stub = client.getComplexStructBlockingStub();
            ComplexStructService.ComplexStructResponse response = stub.invoker(request);
            logger.info("Get response:{}", response);
            return "success";
        } catch (StatusRuntimeException e) {
            logger.info(" Error Received - Error code : {}", e.getStatus().getCode());
        }
        return "error";
    }
}
