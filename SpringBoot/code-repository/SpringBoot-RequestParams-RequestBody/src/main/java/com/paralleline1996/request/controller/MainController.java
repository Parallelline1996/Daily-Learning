/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2020/2/24 23:18
 */

package com.paralleline1996.request.controller;

import com.paralleline1996.request.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping(value = "test1")
    @ResponseBody
    public String test1(@RequestParam(value = "token") String token,
                        @RequestParam(value = "accountId") String accountId) {
        System.out.println("token: " + token + "\naccountId: " + accountId);
        return "hello world";
    }

    @PostMapping(value = "test2")
    @ResponseBody
    public String test2(@RequestParam(value = "length") String length,
                        @RequestParam(value = "md5") String md5) {
        System.out.println("length: " + length + "\nmd5: " + md5);
        return "hello world";
    }

    @PostMapping(value = "test3")
    @ResponseBody
    public String test3(@RequestBody Person person) {
        return person.toString();
    }

    @PostMapping(value = "test4")
    @ResponseBody
    public String test4(@RequestBody Map<String, String> map) {
        Person person = new Person();
        person.setAge(Integer.parseInt(map.get("age")));
        person.setName(map.get("name"));
        person.setWeight(Double.parseDouble(map.get("weight")));
        return person.toString();
    }

    @PostMapping(value = "test5")
    @ResponseBody
    public String test5(@RequestParam(value = "token") String token,
                        @RequestParam(value = "accountId") String accountId,
                        @RequestBody Map<String, String> map) {
        Person person = new Person();
        person.setAge(Integer.parseInt(map.get("age")));
        person.setName(map.get("name"));
        person.setWeight(Double.parseDouble(map.get("weight")));
        return person.toString();
    }
}
