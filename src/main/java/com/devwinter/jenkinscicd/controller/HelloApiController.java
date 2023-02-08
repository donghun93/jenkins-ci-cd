package com.devwinter.jenkinscicd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/jenkins")
public class HelloApiController {

    @GetMapping
    public String hello() {
        // test hello
        log.info("hello");
        return "hello WORLD";
    }
}
