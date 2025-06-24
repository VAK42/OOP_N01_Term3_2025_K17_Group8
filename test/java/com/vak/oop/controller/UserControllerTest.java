package com.vak.oop.controller;

import com.vak.oop.model.User;
import com.vak.oop.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@org.springframework.context.annotation.Import(UserControllerTest.MockConfig.class)
public class UserControllerTest {
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserService userService;

  @TestConfiguration
  static class MockConfig {
    @Bean
    public UserService userService() {
      return Mockito.mock(UserService.class);
    }
  }

  @Test
  void testListLogicOnly() throws Exception {
    Mockito.when(userService.findByRole(any(), any())).thenReturn(new PageImpl<>(List.of(new User())));
    mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(model().attributeExists("users")).andExpect(model().attributeExists("currentPage")).andExpect(model().attributeExists("totalPages"));
  }

  @Test
  void testCreateFormLogicOnly() throws Exception {
    mockMvc.perform(get("/users/new")).andExpect(status().isOk()).andExpect(model().attributeExists("user"));
  }

  @Test
  void testSaveLogicOnly() throws Exception {
    mockMvc.perform(post("/users")).andExpect(status().is3xxRedirection());

    Mockito.verify(userService).save(any());
  }

  @Test
  void testEditFormLogicOnly() throws Exception {
    UUID id = UUID.randomUUID();
    Mockito.when(userService.findById(id)).thenReturn(Optional.of(new User()));
    mockMvc.perform(get("/users/edit/" + id)).andExpect(status().isOk()).andExpect(model().attributeExists("user"));
  }

  @Test
  void testDeleteLogicOnly() throws Exception {
    UUID id = UUID.randomUUID();
    mockMvc.perform(get("/users/delete/" + id)).andExpect(status().is3xxRedirection());
    Mockito.verify(userService).deleteById(id);
  }
}