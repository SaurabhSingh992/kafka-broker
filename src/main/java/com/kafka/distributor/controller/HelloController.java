package com.kafka.distributor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/api/v1/hello", method = RequestMethod.GET)
    public String helloWorld(){
        return "Hello World";
    }

}
