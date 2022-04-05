package com.proday.market.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ruben Malaga
 */
@RestController
@RequestMapping("/greet")
public class HelloWorldController {

    @GetMapping("hi")
    public String greet() {
        return "Hello World!";
    }
}
