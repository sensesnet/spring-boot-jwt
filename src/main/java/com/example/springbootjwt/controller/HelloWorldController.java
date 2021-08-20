package com.example.springbootjwt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @RequestMapping({"/hello"})
    public String firstPage() {
        return "{'page':'Hello world'}";
    }
}
