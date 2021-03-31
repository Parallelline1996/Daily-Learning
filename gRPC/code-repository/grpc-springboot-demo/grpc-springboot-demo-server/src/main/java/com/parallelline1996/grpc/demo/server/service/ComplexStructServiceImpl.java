/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/25 20:45
 */

package com.parallelline1996.grpc.demo.server.service;

import grpc.springboot.demo.api.ComplexStructGrpc;
import grpc.springboot.demo.api.ComplexStructService;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ComplexStructServiceImpl extends ComplexStructGrpc.ComplexStructImplBase {

    private static final Logger logger = LoggerFactory.getLogger(ComplexStructServiceImpl.class);

    @Override
    public void invoker(ComplexStructService.ComplexStructRequest request,
                        StreamObserver<ComplexStructService.ComplexStructResponse> responseObserver) {
        logger.info("receive request:{}", request);
        // 对于基础的数据类型，直接通过 getXxx() 获取
        String msg = request.getMsg();
        double num = request.getNum();
        long longNum = request.getLongNum();
        int shortNum = request.getShortNum();
        boolean check = request.getCheck();
        logger.info("msg:{}, num:{}, longNum:{}, shortNum:{}, check:{}", msg, num, longNum, shortNum, check);

        // 对于数组，可以获取完整的 List 对象，也可以获取数组对象的长度，再通过下标获取特定的对象
        List<Integer> shortNumList = request.getShortNumArrayList();
        int shortNumListNum = request.getShortNumArrayCount();
        int shortNumListFirstNum = request.getShortNumArray(0);
        logger.info("shortNumListNum:{}, shortNumListFirstNum:{}, shortNumList[1]:{}",
                shortNumListNum, shortNumListFirstNum, shortNumList.get(1)) ;

        // 字符串数组同上
        List<String> messageArray = request.getMessageArrayList();
        int messageArrayNum = request.getMessageArrayCount();
        String messageArrayFirstMsg =request.getMessageArray(0);
        logger.info("messageArrayNum:{}, messageArrayFirstMsg:{}, messageArray[1]:{}",
                messageArrayNum, messageArrayFirstMsg, messageArray.get(1)) ;

        // 对于 map 类型，也可以直接获取到完整的对象
        Map<String, String> testMap = request.getTestMapMap();
        for (String key : testMap.keySet()) {
            logger.info("testMap: key:{}, value:{}", key, testMap.get(key));
        }

        // 获取枚举类型 或 直接获取枚举类型的值
        ComplexStructService.enumType enumType = request.getEnumTypeNum();
        int enumTypeValue = request.getEnumTypeNumValue();
        logger.info("enumType:{}, enumTypeValue:{}", enumType, enumTypeValue);

        // 获取字节数组
        byte[] bytes = request.getData().toByteArray();
        logger.info("data:{}", new String(bytes));

        // 构建响应类
        ComplexStructService.Result result = buildResult(100L, "single result");

        // 构建响应数组
        List<ComplexStructService.Result> resultList = new ArrayList<>();
        resultList.add(buildResult(1L, "result list 1"));
        resultList.add(buildResult(2L, "result list 2"));

        // 构建响应 Map 结构
        Map<String, ComplexStructService.Result> resultMap = new HashMap<>();
        resultMap.put("resultMap-key1", buildResult(1L, "result map 1"));
        resultMap.put("resultMap-key2", buildResult(2L, "result map 2"));

        ComplexStructService.ComplexStructResponse response =
                ComplexStructService.ComplexStructResponse
                        .newBuilder()
                        .setResult(result)
                        // 加入定义的 Result 数组
                        .addAllResultList(resultList)
                        // 在 Result 数组上再加入新的 Result 对象
                        .addResultList(buildResult(3L, "result list 3"))
                        // 加入定义的 Map 对象
                        .putAllResultMap(resultMap)
                        // 在 Map 对象上再加入新的元素
                        .putResultMap("resultMap-key3", buildResult(3L, "result map 3"))
                        .build();
        logger.info("return response:{}", response);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private ComplexStructService.Result buildResult(long id, String msg) {
        return ComplexStructService.Result
                .newBuilder()
                .setId(id)
                .setMessage(msg)
                .build();
    }
}
