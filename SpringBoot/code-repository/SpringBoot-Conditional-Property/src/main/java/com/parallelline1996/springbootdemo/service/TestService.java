/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2020/6/24 22:32
 */

package com.parallelline1996.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

//@Service
public class TestService {

    private final static String ENV_AWS = "aws";
    private final static String ENV_INTERNAL = "internal";

    private String env;
    private AwsServiceImpl awsService;
    private HttpServiceImpl httpService;
    private Map<String, IService> serviceMap;

    @Autowired
    public TestService(AwsServiceImpl awsService, HttpServiceImpl httpService, @Value("${env}") String env) {
        this.awsService = awsService;
        this.httpService = httpService;
        this.env = env;
    }

    @PostConstruct
    public void init() {
        // 初始化 Map<String, IService>
        serviceMap = new HashMap<>(4);
        serviceMap.put(ENV_AWS, awsService);
        serviceMap.put(ENV_INTERNAL, httpService);
    }

    public void test() {
        // 根据环境配置，调用不同的服务
        serviceMap.get(env).test();
    }
}
