package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  @Test
  public void testUserFields() {
    User user = new User();
    UUID id = UUID.randomUUID();
    String username = "VAK";
    String password = "42";
    String email = "vak@example.com";
    String role = "admin";
    user.setUserId(id);
    user.setUsername(username);
    user.setPassword(password);
    user.setEmail(email);
    user.setRole(role);
    assertEquals(id, user.getUserId());
    assertEquals(username, user.getUsername());
    assertEquals(password, user.getPassword());
    assertEquals(email, user.getEmail());
    assertEquals(role, user.getRole());
  }
}