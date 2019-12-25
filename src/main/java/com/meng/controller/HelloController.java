package com.meng.controller;

import com.meng.entity.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello,中文";
    }

    @RequestMapping("/demo")
    public Demo getDemo(){
        Demo demo = new Demo(1, "meng");
        return demo;
    }
}
