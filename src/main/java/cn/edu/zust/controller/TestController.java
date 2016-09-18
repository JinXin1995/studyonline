package cn.edu.zust.controller;

import cn.edu.zust.service.TestServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by King on 2016/8/18 0018.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private TestServiceI testService;

    @RequestMapping("/test")
    public String test() {
        logger.error("access test page");
        return "test";
    }

    @RequestMapping("add-user")
    public String addUser() {
        String name = "user" + (int) (Math.random() * 1000);
        testService.addUser(name);
        return "test";
    }
}