package com.example.ForeignExchangeSystem.userService;

import com.example.ForeignExchangeSystem.model.RestUser;
import com.example.ForeignExchangeSystem.model.UserDetailsRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface UserService2 {
    public RestUser createUser(UserDetailsRequestModel userDetails);

}
