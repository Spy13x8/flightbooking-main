package com.flightBooking.flightBooking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @GetMapping("/Test")
    public String testing(){
        return "ok sab changa si";
    }
}
