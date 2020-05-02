package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.Service.UserService;
import com.example.ForeignExchangeSystem.model.User;
import com.example.ForeignExchangeSystem.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WalletController {

    @Autowired
    private UserService userService;

    @GetMapping("/wallet")
    public String Wallet(Model model) {
        String username = Utils.getLoggedUser();
        User user = userService.findUserByMail(username);
        int role_num = user.getRoles().iterator().next().getId();
        user.setRole_num(role_num);

        model.addAttribute("wallet", user.getWallet());
        model.addAttribute("user", user);

        return "wallet";
    }

}
