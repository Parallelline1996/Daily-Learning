/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2019/11/25 22:07
 */

package com.parallelline1996;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.parallelline1996.dao")
public class Starter {

    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}
