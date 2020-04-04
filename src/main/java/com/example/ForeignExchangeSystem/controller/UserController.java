package com.example.ForeignExchangeSystem.controller;

import com.example.ForeignExchangeSystem.DTO.UserDTO;
import com.example.ForeignExchangeSystem.model.UpdateUserDetailsRequestModel;
import com.example.ForeignExchangeSystem.model.UserDetailsRequestModel;
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

    Map<String, UserDTO> users;
    //produces ustala typ zwracanego pliku
    @GetMapping(path="/{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId)
    {
        if(users.containsKey(userId))
        {
            return new ResponseEntity<UserDTO>(users.get(userId), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
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

    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
    {
        UserDTO value = new UserDTO();
        value.setEmail(userDetails.getEmail());
        value.setMoney(userDetails.getMoney());
        value.setPassword(userDetails.getPassword());

        String userId = UUID.randomUUID().toString();
        value.setId(userId);
        if(users == null) users = new HashMap<>();
        users.put(userId, value);
        return new ResponseEntity<UserDTO>(value, HttpStatus.OK);
    }
    @PutMapping(path="/{userId}", consumes = {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public UserDTO updateUser(@PathVariable String userId, @RequestBody UpdateUserDetailsRequestModel userDetails)
    {
        UserDTO storedUserDetails= users.get(userId);
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
