package com.example.demoh2.Controller;

import com.example.demoh2.model.User;
import com.example.demoh2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/test")

public class TestController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping()
    public String message() {
        return "hello World";
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userRepo.save(user);
    }

    @GetMapping("/findById")
    public Optional<User> getById(@RequestParam int id) {
        return userRepo.findById(id);
    }

    @GetMapping("/findAll")
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        Optional<User> userData = userRepo.findById(id);
        if (userData.isPresent()) {
            userData.get().setName(user.getFirstName());
            userData.get().setLastname(user.getLastName());
            return userRepo.save(userData.get());
        }
        return null;
    }


}