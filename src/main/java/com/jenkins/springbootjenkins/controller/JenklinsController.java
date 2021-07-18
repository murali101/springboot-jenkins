package com.jenkins.springbootjenkins.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JenklinsController {

    @GetMapping("/")
    public String getData() {
        return "Data from Jenkins 1.0";
    }
}
