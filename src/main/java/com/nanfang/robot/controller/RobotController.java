package com.nanfang.robot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RobotController {
    @RequestMapping("/")
    public String hello(){
        return "hello springboot";
    }
}
