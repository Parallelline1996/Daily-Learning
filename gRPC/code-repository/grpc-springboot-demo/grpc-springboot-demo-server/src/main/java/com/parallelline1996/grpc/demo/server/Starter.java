/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2021/3/18 20:19
 */

package com.parallelline1996.grpc.demo.server;

import com.parallelline1996.grpc.demo.server.configuration.GrpcServerConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Starter implements CommandLineRunner {

    private GrpcServerConfiguration configuration;

    public Starter(GrpcServerConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void run(String... args) throws Exception {
        configuration.start();
    }

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}