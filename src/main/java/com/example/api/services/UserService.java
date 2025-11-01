package com.example.api.services;
import com.example.api.models.User;
import com.example.api.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> list() { return repo.findAll(); }
    public User get(Long id) { return repo.findById(id).orElse(null); }
    public User save(User user) { return repo.save(user); }
    public void delete(Long id) { repo.deleteById(id); }
    public boolean login(String email, String password) {
        User user = repo.findByEmailAndPassword(email, password);
        return user != null;
    }
}