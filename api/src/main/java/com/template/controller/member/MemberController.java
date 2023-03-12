package com.template.controller.member;

import com.template.core.service.member.MemberService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/members")
    public ResponseEntity registerMember(
            @RequestBody @Validated RegisterMemberReq request
    ){
        memberService.registerMember(request.toServiceDto(passwordEncoder));
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
