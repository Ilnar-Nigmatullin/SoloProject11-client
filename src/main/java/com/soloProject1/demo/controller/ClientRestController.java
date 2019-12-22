package com.soloProject1.demo.controller;

import com.soloProject1.demo.model.User;
import com.soloProject1.demo.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ClientRestController {
    private RestService restService;

    @Autowired
    public ClientRestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/admin/rest/all")
    public List<User> readUsers() {
        return restService.readUsers();
    }

    @PostMapping("/admin/rest/create")
    public void addUser(@RequestBody User user) {
        restService.addUser(user);
    }

    @PutMapping("/admin/rest/update/{id}")
    public void updateUser(@PathVariable User user) {
        restService.updateUser(user);
    }

    @DeleteMapping("/admin/rest/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        restService.deleteUser(id);
    }

    @GetMapping("/admin/rest/update/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
       return restService.getUserById(id);
    }
}
