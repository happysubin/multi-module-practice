package com.template.controller;

import com.template.core.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping
    public String registerHello(){
        Long aLong = helloService.registerHello();
        return String.valueOf(aLong);
    }
}
