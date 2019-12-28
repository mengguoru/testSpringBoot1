package com.meng.controller;

import com.meng.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class IndexController {
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("username", "管理员");
        return modelAndView;
    }

    @RequestMapping("/test")
    public void test() throws IOException {
        userService = new UserService();
        var users = userService.findAll();
        if(users.size() > 0){
            for(var i:users)
                System.out.println(i);
        }
    }
}
