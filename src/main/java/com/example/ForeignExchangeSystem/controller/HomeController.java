package com.example.ForeignExchangeSystem.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @RequestMapping("/test")
    public String Home() {
        return "testview/testhtml";
    }
}
