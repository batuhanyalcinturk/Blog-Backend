//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.graysan.blog.controller;

import com.graysan.blog.entities.User;
import com.graysan.blog.service.UserService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/users"})
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User newUser) {
        return this.userService.saveOneUser(newUser);
    }

    @GetMapping({"/{userId}"})
    public User getOneUserById(@PathVariable Long userId) {
        return this.userService.getOneUserById(userId);
    }

    @PutMapping({"/{userId}"})
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        return this.userService.updateOneUser(userId, newUser);
    }

    @DeleteMapping({"/{userId}"})
    public void deleteOneUser(@PathVariable Long userId) {
        this.userService.deleteById(userId);
    }
}
