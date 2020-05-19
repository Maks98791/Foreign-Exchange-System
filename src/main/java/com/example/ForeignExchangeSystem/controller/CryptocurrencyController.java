package com.example.ForeignExchangeSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CryptocurrencyController {

    @GetMapping("/cryptocurrency")
    public String Cryptocurrency() {
        return "cryptocurrency";
    }
}
