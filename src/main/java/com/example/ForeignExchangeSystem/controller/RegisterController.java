package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.Service.UserService;
import com.example.ForeignExchangeSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Locale;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/register")
    public String Register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping(value = "/adduser")
    public String registerAction(User user, BindingResult result, Model model, Locale locale)
    {
        String returnPage = null;
        User userExist = userService.findUserByMail(user.getEmail());
        if(userExist != null)
        {
            result.rejectValue("email", messageSource.getMessage("error.userEmailNotMatch", null, locale));
        }
        if (result.hasErrors())
        {
            returnPage = "register";
        }
        else
        {
            userService.saveUser(user);
            model.addAttribute("message", messageSource.getMessage("user.register.success", null , locale));
            model.addAttribute("user", new User());
            returnPage = "register";
        }
        return returnPage;
    }


}
