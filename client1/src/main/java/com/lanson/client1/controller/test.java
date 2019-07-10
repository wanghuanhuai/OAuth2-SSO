package com.lanson.client1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class test {
    @GetMapping("/currentUser")
    @ResponseBody
    public Authentication me(Authentication authentication) {
        return authentication;
    }
}
