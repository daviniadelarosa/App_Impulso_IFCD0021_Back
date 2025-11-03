package com.example.api.controllers;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.api.models.User;
import com.example.api.services.UserService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<User> list() { return service.list(); }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) { return service.get(id); }

    @PostMapping("register")
    public User register(@RequestBody User user) { return service.save(user); }

    @PostMapping("login")
    public Map<String, String> login(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        boolean isAuthenticated = service.login(user.getEmail(), user.getPassword());
        if (isAuthenticated) {
            response.put("message", "login ok");
        } else {
            response.put("message", "usuario o contraseña incorrectos");
        }
        return response;
    }


    @PatchMapping("/{id}")
    public User update(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.delete(id); }
}
