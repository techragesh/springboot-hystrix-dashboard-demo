package com.techjava.springboothystrixdemo.resource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoResource {

    @HystrixCommand(fallbackMethod = "fallbackHello", commandKey = "hello", groupKey = "hello")
    @GetMapping("/hello")
    public String hello(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Failed!");
        }
        return "Hello World from hello method";
    }

    @HystrixCommand(fallbackMethod = "fallbackHello", commandKey = "helloAnother", groupKey = "helloAnother")
    @GetMapping("/helloAnother")
    public String helloAnother(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Failed!");
        }
        return "Hello World from helloAnother method";
    }

    public String fallbackHello(){
        return "FallBack Hello Initiated";
    }
}
