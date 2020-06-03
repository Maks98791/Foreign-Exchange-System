package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.model.User;
import com.example.ForeignExchangeSystem.shared.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculatorController {


    @GetMapping("/calculator")
    public String Login() {
        return "calculator";
    }

}
