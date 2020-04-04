package com.example.ForeignExchangeSystem.userService;

import com.example.ForeignExchangeSystem.model.RestUser;
import com.example.ForeignExchangeSystem.model.UserDetailsRequestModel;
import com.example.ForeignExchangeSystem.shared.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    Map<String, RestUser> users;
    Utils utils;

    public UserServiceImp() {
    }

    //trzeba dodać adnotacje żeby user controller mogl wstrzyknac te utils
    //jak ktos nie rozumie to polecam poczytać o spring injection
    @Autowired
    public UserServiceImp(Utils utils) {
        this.utils = utils;
    }

    @Override
    public RestUser createUser(UserDetailsRequestModel userDetails) {

        RestUser value = new RestUser();
        value.setEmail(userDetails.getEmail());
        value.setMoney(userDetails.getMoney());
        value.setPassword(userDetails.getPassword());

        String userId = utils.gnerateUserId();
        value.setId(userId);
        if(users == null) users = new HashMap<>();
        users.put(userId, value);

        return value;
    }
}
