package com.vak.oop.service;

import com.vak.oop.model.User;
import com.vak.oop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public boolean save(User user) {
        try {
            if (userRepository.existsByUsername(user.getUsername()) ||
                    userRepository.existsByEmail(user.getEmail())) {
                return false;
            }
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean update(UUID id, User updated) {
        try {
            return userRepository.findById(id).map(u -> {
                u.setUsername(updated.getUsername());
                u.setPassword(updated.getPassword());
                u.setEmail(updated.getEmail());
                u.setRole(updated.getRole());
                userRepository.save(u);
                return true;
            }).orElse(false);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete(UUID id) {
        try {
            if (userRepository.existsById(id)) {
                userRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}

