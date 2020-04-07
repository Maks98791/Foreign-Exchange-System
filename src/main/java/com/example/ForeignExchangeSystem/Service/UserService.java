package com.example.ForeignExchangeSystem.Service;

import com.example.ForeignExchangeSystem.model.User;

public interface UserService {

    public User findUserByMail (String email);
    public void saveUser(User user);
}
