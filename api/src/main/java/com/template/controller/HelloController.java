package com.template.controller;

import com.template.core.service.HelloService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping
    public Dto registerHello(@RequestParam String name){
        Long aLong = helloService.registerHello();
        return new Dto(String.valueOf(aLong) + ": id, name: " + name);
    }

    @Getter
    @NoArgsConstructor
    static class Dto{
        private String message;

        public Dto(String message) {
            this.message = message;
        }
    }
}
