package com.jenkins.springbootjenkins.controller;

import com.jenkins.springbootjenkins.config.ExampleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenklinsController {

    @Autowired
    private ExampleConfig exampleConfig;

    @GetMapping("/")
    public String getData() {
        return exampleConfig.getData();
    }
}
