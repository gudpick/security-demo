package com.notyfyd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @GetMapping("/morning")
    public String getGreetings() {
        return "Good Morning";
    }
}
