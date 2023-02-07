package com.devwinter.jenkinscicd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/jenkins")
public class HelloApiController {

    @GetMapping
    public String hello() {
        // test hello
        return "hello";
    }
}
