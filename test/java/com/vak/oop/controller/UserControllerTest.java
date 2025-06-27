package com.vak.oop.controller;

import com.vak.oop.model.User;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {
  private UserService userService;
  private UserController controller;
  private Model model;
  private User user;

  @BeforeEach
  void setup() {
    userService = mock(UserService.class);
    controller = new UserController(userService);
    model = mock(Model.class);
    user = new User();
    user.setUsername("john");
    user.setPassword("pass");
    user.setEmail("john@example.com");
    user.setRole("user");
  }

  @Test
  void testSaveNewUserSuccess() {
    user.setUserId(null);
    when(userService.existsByUsername("john")).thenReturn(false);
    String result = controller.save(user, model);
    assertEquals("redirect:/users", result);
    verify(userService).save(user);
  }

  @Test
  void testSaveEditedUserDuplicateUsername() {
    UUID id = UUID.randomUUID();
    user.setUserId(id);
    user.setUsername("newname");
    User existing = new User();
    existing.setUserId(id);
    existing.setUsername("oldname");
    when(userService.findById(id)).thenReturn(Optional.of(existing));
    when(userService.existsByUsername("newname")).thenReturn(true);
    String result = controller.save(user, model);
    assertEquals("index", result);
    verify(model).addAttribute("error", "Already Existed");
  }
}