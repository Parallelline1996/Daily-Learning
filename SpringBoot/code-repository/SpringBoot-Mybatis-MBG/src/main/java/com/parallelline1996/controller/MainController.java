/**
 * @Author: parallelline1996
 * @Email: chenyu1996a@163.com
 * @Date: 2019/11/25 23:43
 */

package com.parallelline1996.controller;

import com.parallelline1996.bean.People;
import com.parallelline1996.dao.PeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private PeopleMapper peopleMapper;

    @Autowired
    MainController(PeopleMapper peopleMapper) {
        this.peopleMapper = peopleMapper;
    }

    @GetMapping("/addNewUser")
    public void addNewUser() {
        People people = new People(null, 12, 40.5, 153.4, "Tim");
        peopleMapper.insert(people);
    }
}
