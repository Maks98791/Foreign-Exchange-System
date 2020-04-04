package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.DTO.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    //produces ustala typ zwracanego pliku
    @GetMapping(path="/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserDTO getUser(@PathVariable String userId)
    {
        UserDTO value = new UserDTO();
        value.setEmail("kasia@gmail.com");
        value.setMoney(20.00);
        
        return value;
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
    @PostMapping
    public String createUser()
    {
        return "create user was called";
    }
    @PutMapping
    public String updateUser()
    {
        return "update user was called";
    }
    @DeleteMapping
    public String deleteUser()
    {
        return "delete user was called";
    }

}
