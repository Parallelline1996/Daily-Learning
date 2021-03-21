/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/19 20:12
 */

package com.parallelline1996.grpc.demo.consumer;

import com.parallelline1996.grpc.demo.consumer.client.GrpcClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter implements CommandLineRunner {

    private GrpcClient configuration;

    public Starter(GrpcClient configuration) {
        this.configuration = configuration;
    }

    @Override
    public void run(String... args) {
        configuration.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}