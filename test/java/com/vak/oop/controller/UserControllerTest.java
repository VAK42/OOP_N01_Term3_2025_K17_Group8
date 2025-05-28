package com.vak.oop.controller;

import com.vak.oop.model.UserEntity;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class UserControllerTest {
  private UserService userService;
  private UserLogic logic;

  @BeforeEach
  public void setup() {
    userService = mock(UserService.class);
    logic = new UserLogic(userService);
  }

  @Test
  public void testDeleteUserConfirmed() {
    UserEntity user = new UserEntity();
    logic.deleteUser(user, true);
    verify(userService).deleteUser(user);
  }

  @Test
  public void testDeleteUserCancelled() {
    UserEntity user = new UserEntity();
    logic.deleteUser(user, false);
    verify(userService, never()).deleteUser(user);
  }

  @Test
  public void testDeleteUserNull() {
    logic.deleteUser(null, true);
    verify(userService, never()).deleteUser(any());
  }

  static class UserLogic {
    private final UserService userService;

    public UserLogic(UserService userService) {
      this.userService = userService;
    }

    public void deleteUser(UserEntity selected, boolean userConfirmed) {
      if (selected != null && userConfirmed) {
        userService.deleteUser(selected);
      }
    }
  }
}