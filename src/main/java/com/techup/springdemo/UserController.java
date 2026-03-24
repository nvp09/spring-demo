package com.techup.springdemo;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //  GET all users
    @GetMapping
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //  POST create user
    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }

    //  PUT update user
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existing = userRepository.findById(id).orElseThrow();

        existing.setName(user.getName());
        existing.setEmail(user.getEmail());

        return userRepository.save(existing);
    }

    //  DELETE user
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}