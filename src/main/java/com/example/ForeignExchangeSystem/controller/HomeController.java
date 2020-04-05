package com.example.ForeignExchangeSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(value = "/")
    public ModelAndView Home() {

        final ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }
    @GetMapping("/register")
    public String Register() {
        return "register";
    }
}
