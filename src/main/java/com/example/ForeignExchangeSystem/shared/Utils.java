package com.example.ForeignExchangeSystem.shared;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;
//chodzi o to że w tej klasie moga byc metody używane przez Serwisy żeby nie zasmiecac ich dlugim kodem
//adnotacja potrzebna zeby mozna bylo zrobic autowired
@Service
public class Utils {

    public static String getLoggedUser(){
        String username = null;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            username = auth.getName();
        }
        return username;
    }
}
