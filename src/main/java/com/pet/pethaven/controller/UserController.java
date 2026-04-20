package com.pet.pethaven.controller;

import com.pet.pethaven.dto.UserDTO;
import com.pet.pethaven.model.User;
import com.pet.pethaven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user/")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }
    @GetMapping("/getUserByEmail")
    public ResponseEntity<UserDTO> getUserDTO(@RequestParam("email")String email) {
       return new ResponseEntity<>(userService.getUserByEmail(email),HttpStatus.OK);
    }
}
