/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2020/6/10 23:19
 */

package com.parallelline1996.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private IService iService;

    @Autowired
    public MainService(IService iService) {
        this.iService = iService;
    }

    @Scheduled(cron = "0/5 * * * * *")
    public void test() {
        iService.test();
    }
}
