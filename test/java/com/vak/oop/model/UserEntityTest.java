package com.vak.oop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {
  @Test
  public void testUsernameGetterSetter() {
    UserEntity user = new UserEntity();
    user.setUsername("Admin");
    assertEquals("Admin", user.getUsername());
  }
}