package com.multinationals.visa.api.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RootController {
    @GetMapping("/")
    public String testing() {
        return "200 OK";
    }
}
