/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2019/11/24 22:22
 */

package com.parallelline1996.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Configuration(value = "application.properties")
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
