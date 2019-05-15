package com.security.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GreetingRestController {

    @GetMapping("/greeting")
    String greeting(Principal principal) {
        return "Hello, " + principal.getName() + "!";
    }

}
