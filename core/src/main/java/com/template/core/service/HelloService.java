package com.template.core.service;


import com.template.core.domain.Hello;
import com.template.core.domain.HelloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HelloService {

    private final HelloRepository helloRepository;

    @Transactional
    public Long registerHello(){
        Hello hello = new Hello();
        helloRepository.save(hello);
        return hello.getId();
    }
}
