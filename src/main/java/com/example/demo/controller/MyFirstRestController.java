package com.example.demo.controller;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyFirstRestController {

    private static final Logger logger = LoggerFactory.getLogger(MyFirstRestController.class);
    @GetMapping("say-something")
    public String saySomething(){

        logger.info("/say-something");
        logger.debug("is it visible???");
        return "Hello RestController";
    }

}
