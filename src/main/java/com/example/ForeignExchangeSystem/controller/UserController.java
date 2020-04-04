package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.model.RestUser;
import com.example.ForeignExchangeSystem.model.UpdateUserDetailsRequestModel;
import com.example.ForeignExchangeSystem.model.UserDetailsRequestModel;
import com.example.ForeignExchangeSystem.userService.UserService;
import com.example.ForeignExchangeSystem.userService.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    Map<String, RestUser> users;

    @Autowired
    UserService userService;
    //produces ustala typ zwracanego pliku
    @GetMapping(path="/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<RestUser> getUser(@PathVariable String userId)
    {
        if(users.containsKey(userId))
        {
            return new ResponseEntity<RestUser>(users.get(userId), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<RestUser>(HttpStatus.NO_CONTENT);
        }

    }

    // /users?page=1&limit=50
    // default value sprawia że parametr nie jest konieczny i wartosc domyslna to 1
    // parametr limit jest konieczny
    // można też użyć require=false ale sprawdza sie tylko dla typu string
    @GetMapping()
    public String getUser(@RequestParam(value="page", defaultValue = "1") int page,
                          @RequestParam(value="limit") int limit)
    {
        return "get user was called with page ="+page +" and limit "+limit;
    }
    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})

    public ResponseEntity<RestUser> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
    {
        RestUser value = userService.createUser(userDetails);
        return new ResponseEntity<RestUser>(value, HttpStatus.OK);
    }
    @PutMapping(path="/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public RestUser updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails)
    {
        RestUser storedUserDetails= users.get(userId);
        storedUserDetails.setMoney(userDetails.getMoney());
        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @DeleteMapping(path="/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId)
    {
        users.remove(userId);
        return ResponseEntity.noContent().build();
    }

}
