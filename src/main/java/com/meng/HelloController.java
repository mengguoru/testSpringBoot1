package com.meng;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index(){
        return "Hello,Spring Boot";
    }

    @RequestMapping("/hello")
    public String hello(){
        return "Hello,中文";
    }
}
