package com.example.ForeignExchangeSystem.shared;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.UUID;
//chodzi o to że w tej klasie moga byc metody używane przez Serwisy żeby nie zasmiecac ich dlugim kodem
//adnotacja potrzebna zeby mozna bylo zrobic autowired
@Service
public class Utils {
    public String gnerateUserId(){
        return UUID.randomUUID().toString();
    }

}
