/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2020/6/10 23:17
 */

package com.parallelline1996.springbootdemo.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@ConditionalOnProperty(
        name = "env",
        havingValue = "aws"
)
public class AwsServiceImpl implements IService{

    @PostConstruct
    public void init() {
        System.out.println("AwsServiceImpl init ... ");
    }

    @Override
    public void test() {
        System.out.println("AwsServiceImpl test ... ");
    }
}
