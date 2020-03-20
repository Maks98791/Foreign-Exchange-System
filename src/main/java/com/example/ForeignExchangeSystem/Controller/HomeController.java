package com.example.ForeignExchangeSystem.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping
    public String Home() {
        return "Hello World";
    }
}
